package Domain;

import Fabrica.FabricaDeModelo;
import Marca.Modelo;
import Representante.Vendas;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Arrays;

public class Jpql {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ebac");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // Criar fábricas de modelo
        FabricaDeModelo fabrica1 = new FabricaDeModelo();
        fabrica1.setAnoDeFabricacao(2020);
        fabrica1.setMarca("Toyota");

        FabricaDeModelo fabrica2 = new FabricaDeModelo();
        fabrica2.setAnoDeFabricacao(2021);
        fabrica2.setMarca("Honda");

        FabricaDeModelo fabrica3 = new FabricaDeModelo();
        fabrica3.setAnoDeFabricacao(2019);
        fabrica3.setMarca("Ford");

        FabricaDeModelo fabrica4 = new FabricaDeModelo();
        fabrica4.setAnoDeFabricacao(2022);
        fabrica4.setMarca("Chevrolet");

        FabricaDeModelo fabrica5 = new FabricaDeModelo();
        fabrica5.setAnoDeFabricacao(2020);
        fabrica5.setMarca("Volkswagen");

        FabricaDeModelo fabrica6 = new FabricaDeModelo();
        fabrica6.setAnoDeFabricacao(2023);
        fabrica6.setMarca("Hyundai");

        em.persist(fabrica1);
        em.persist(fabrica2);
        em.persist(fabrica3);
        em.persist(fabrica4);
        em.persist(fabrica5);
        em.persist(fabrica6);

        // Criar modelos
        Modelo modelo1 = new Modelo();
        modelo1.setModeloDoCarro("Corolla");
        modelo1.setAnoDeFabricacao(2020);
        modelo1.setMarca("Toyota");
        modelo1.setPreco(90000);
        modelo1.setFabricaDeModelo(fabrica1);

        Modelo modelo2 = new Modelo();
        modelo2.setModeloDoCarro("Civic");
        modelo2.setAnoDeFabricacao(2021);
        modelo2.setMarca("Honda");
        modelo2.setPreco(95000);
        modelo2.setFabricaDeModelo(fabrica2);

        Modelo modelo3 = new Modelo();
        modelo3.setModeloDoCarro("Focus");
        modelo3.setAnoDeFabricacao(2019);
        modelo3.setMarca("Ford");
        modelo3.setPreco(80000);
        modelo3.setFabricaDeModelo(fabrica3);

        Modelo modelo4 = new Modelo();
        modelo4.setModeloDoCarro("Cruze");
        modelo4.setAnoDeFabricacao(2022);
        modelo4.setMarca("Chevrolet");
        modelo4.setPreco(92000);
        modelo4.setFabricaDeModelo(fabrica4);

        Modelo modelo5 = new Modelo();
        modelo5.setModeloDoCarro("Golf");
        modelo5.setAnoDeFabricacao(2020);
        modelo5.setMarca("Volkswagen");
        modelo5.setPreco(89000);
        modelo5.setFabricaDeModelo(fabrica5);

        Modelo modelo6 = new Modelo();
        modelo6.setModeloDoCarro("Elantra");
        modelo6.setAnoDeFabricacao(2023);
        modelo6.setMarca("Hyundai");
        modelo6.setPreco(98000);
        modelo6.setFabricaDeModelo(fabrica6);

        Modelo modelo7 = new Modelo();
        modelo7.setModeloDoCarro("Prius");
        modelo7.setAnoDeFabricacao(2020);
        modelo7.setMarca("Toyota");
        modelo7.setPreco(100000);
        modelo7.setFabricaDeModelo(fabrica1);

        Modelo modelo8 = new Modelo();
        modelo8.setModeloDoCarro("HR-V");
        modelo8.setAnoDeFabricacao(2021);
        modelo8.setMarca("Honda");
        modelo8.setPreco(105000);
        modelo8.setFabricaDeModelo(fabrica2);

        Modelo modelo9 = new Modelo();
        modelo9.setModeloDoCarro("Ranger");
        modelo9.setAnoDeFabricacao(2019);
        modelo9.setMarca("Ford");
        modelo9.setPreco(120000);
        modelo9.setFabricaDeModelo(fabrica3);

        Modelo modelo10 = new Modelo();
        modelo10.setModeloDoCarro("Onix");
        modelo10.setAnoDeFabricacao(2022);
        modelo10.setMarca("Chevrolet");
        modelo10.setPreco(75000);
        modelo10.setFabricaDeModelo(fabrica4);

        em.persist(modelo1);
        em.persist(modelo2);
        em.persist(modelo3);
        em.persist(modelo4);
        em.persist(modelo5);
        em.persist(modelo6);
        em.persist(modelo7);
        em.persist(modelo8);
        em.persist(modelo9);
        em.persist(modelo10);

        // Criar vendas
        Vendas venda1 = new Vendas();
        venda1.setAnoDeFabricacao(2020);
        venda1.setModelo("Corolla");
        venda1.setMarca("Toyota");
        venda1.setValorDeVenda(93000);
        venda1.setComissao(3000);
        venda1.setModelosVendidos(Arrays.asList(modelo1, modelo7));

        Vendas venda2 = new Vendas();
        venda2.setAnoDeFabricacao(2021);
        venda2.setModelo("Civic");
        venda2.setMarca("Honda");
        venda2.setValorDeVenda(97000);
        venda2.setComissao(2000);
        venda2.setModelosVendidos(Arrays.asList(modelo2, modelo8));

        em.persist(venda1);
        em.persist(venda2);

        em.getTransaction().commit();

        em.close();
        emf.close();

        System.out.println("Dados inseridos com sucesso!");
    }
}
