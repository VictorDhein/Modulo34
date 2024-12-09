package Marca;

import Fabrica.FabricaDeModelo;
import Representante.Vendas;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modeloDoCarro;
    private int anoDeFabricacao;
    private String marca;
    private double preco;

    @ManyToOne
    @JoinColumn(name = "fabrica_id")
    private FabricaDeModelo fabricaDeModelo;

    @ManyToMany(mappedBy = "modelosVendidos")
    private List<Vendas> vendas;

    public Modelo(String corolla, int i, String toyota, int i1, FabricaDeModelo fabrica1) {

    }

    public Modelo() {

    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModeloDoCarro() {
        return modeloDoCarro;
    }

    public void setModeloDoCarro(String modeloDoCarro) {
        this.modeloDoCarro = modeloDoCarro;
    }

    public int getAnoDeFabricacao() {
        return anoDeFabricacao;
    }

    public void setAnoDeFabricacao(int anoDeFabricacao) {
        this.anoDeFabricacao = anoDeFabricacao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public FabricaDeModelo getFabricaDeModelo() {
        return fabricaDeModelo;
    }

    public void setFabricaDeModelo(FabricaDeModelo fabricaDeModelo) {
        this.fabricaDeModelo = fabricaDeModelo;
    }

    public List<Vendas> getVendas() {
        return vendas;
    }

    public void setVendas(List<Vendas> vendas) {
        this.vendas = vendas;
    }
}
