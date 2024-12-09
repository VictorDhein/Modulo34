package Domain;

import Fabrica.FabricaDeModelo;
import Marca.Modelo;
import Representante.Vendas;

import java.sql.*;
import java.util.Arrays;

public class Jdbc {

    private static final String DB_URL = "jdbc:mysql://localhost:5432/Modulo34";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "987546";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            connection.setAutoCommit(false);

            // Inserir fábricas de modelo
            FabricaDeModelo fabrica1 = new FabricaDeModelo(2020, "Toyota");
            FabricaDeModelo fabrica2 = new FabricaDeModelo(2021, "Honda");
            FabricaDeModelo fabrica3 = new FabricaDeModelo(2019, "Ford");
            FabricaDeModelo fabrica4 = new FabricaDeModelo(2022, "Chevrolet");
            FabricaDeModelo fabrica5 = new FabricaDeModelo(2020, "Volkswagen");
            FabricaDeModelo fabrica6 = new FabricaDeModelo(2023, "Hyundai");

            insertFabrica(connection, fabrica1);
            insertFabrica(connection, fabrica2);
            insertFabrica(connection, fabrica3);
            insertFabrica(connection, fabrica4);
            insertFabrica(connection, fabrica5);
            insertFabrica(connection, fabrica6);

            // Inserir modelos
            Modelo modelo1 = new Modelo("Corolla", 2020, "Toyota", 90000, fabrica1);
            Modelo modelo2 = new Modelo("Civic", 2021, "Honda", 95000, fabrica2);
            Modelo modelo3 = new Modelo("Focus", 2019, "Ford", 80000, fabrica3);
            Modelo modelo4 = new Modelo("Cruze", 2022, "Chevrolet", 92000, fabrica4);
            Modelo modelo5 = new Modelo("Golf", 2020, "Volkswagen", 89000, fabrica5);
            Modelo modelo6 = new Modelo("Elantra", 2023, "Hyundai", 98000, fabrica6);

            insertModelo(connection, modelo1);
            insertModelo(connection, modelo2);
            insertModelo(connection, modelo3);
            insertModelo(connection, modelo4);
            insertModelo(connection, modelo5);
            insertModelo(connection, modelo6);

            // Inserir vendas
            Vendas venda1 = new Vendas(2020, "Corolla", "Toyota", 93000, 3000, Arrays.asList(modelo1));
            Vendas venda2 = new Vendas(2021, "Civic", "Honda", 97000, 2000, Arrays.asList(modelo2));

            insertVenda(connection, venda1);
            insertVenda(connection, venda2);

            connection.commit();
            System.out.println("Dados inseridos com sucesso!");

            // Consultas
            consultarModelosPorAno(connection, 2020);
            consultarModelosPorPreco(connection, 90000);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertFabrica(Connection connection, FabricaDeModelo fabrica) throws SQLException {
        String sql = "INSERT INTO fabrica_de_modelo (ano_de_fabricacao, marca) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, fabrica.getAnoDeFabricacao());
            ps.setString(2, fabrica.getMarca());
            ps.executeUpdate();
        }
    }

    private static void insertModelo(Connection connection, Modelo modelo) throws SQLException {
        String sql = "INSERT INTO modelo (modelo_do_carro, ano_de_fabricacao, marca, preco, fabrica_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, modelo.getModeloDoCarro());
            ps.setInt(2, modelo.getAnoDeFabricacao());
            ps.setString(3, modelo.getMarca());
            ps.setDouble(4, modelo.getPreco());
            ps.setInt(5, getFabricaId(connection, modelo.getFabricaDeModelo()));
            ps.executeUpdate();
        }
    }

    private static int getFabricaId(Connection connection, FabricaDeModelo fabrica) throws SQLException {
        String sql = "SELECT id FROM fabrica_de_modelo WHERE ano_de_fabricacao = ? AND marca = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, fabrica.getAnoDeFabricacao());
            ps.setString(2, fabrica.getMarca());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        throw new SQLException("Fábrica não encontrada: " + fabrica.getMarca());
    }

    private static void insertVenda(Connection connection, Vendas venda) throws SQLException {
        String sql = "INSERT INTO vendas (ano_de_fabricacao, modelo, marca, valor_de_venda, comissao) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, venda.getAnoDeFabricacao());
            ps.setString(2, venda.getModelo());
            ps.setString(3, venda.getMarca());
            ps.setDouble(4, venda.getValorDeVenda());
            ps.setDouble(5, venda.getComissao());
            ps.executeUpdate();
        }
    }

    private static void consultarModelosPorAno(Connection connection, int ano) throws SQLException {
        String sql = "SELECT modelo_do_carro FROM modelo WHERE ano_de_fabricacao = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, ano);
            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("Modelos fabricados no ano " + ano + ":");
                while (rs.next()) {
                    System.out.println(rs.getString("modelo_do_carro"));
                }
            }
        }
    }

    private static void consultarModelosPorPreco(Connection connection, double preco) throws SQLException {
        String sql = "SELECT modelo_do_carro FROM modelo WHERE preco > ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, preco);
            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("Modelos com preço maior que " + preco + ":");
                while (rs.next()) {
                    System.out.println(rs.getString("modelo_do_carro"));
                }
            }
        }
    }
}
