package Representante;

import Marca.Modelo;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Vendas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int anoDeFabricacao;
    private String modelo;
    private String marca;
    private double valorDeVenda;
    private double comissao;

    @ManyToMany
    @JoinTable(
            name = "venda_modelos",
            joinColumns = @JoinColumn(name = "venda_id"),
            inverseJoinColumns = @JoinColumn(name = "modelo_id")
    )
    private List<Modelo> modelosVendidos;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAnoDeFabricacao() {
        return anoDeFabricacao;
    }

    public void setAnoDeFabricacao(int anoDeFabricacao) {
        this.anoDeFabricacao = anoDeFabricacao;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getValorDeVenda() {
        return valorDeVenda;
    }

    public void setValorDeVenda(double valorDeVenda) {
        this.valorDeVenda = valorDeVenda;
    }

    public double getComissao() {
        return comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    public List<Modelo> getModelosVendidos() {
        return modelosVendidos;
    }

    public void setModelosVendidos(List<Modelo> modelosVendidos) {
        this.modelosVendidos = modelosVendidos;
    }
}
