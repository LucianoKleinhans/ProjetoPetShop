package br.lajotasoftware.petshop.classes;

import java.io.Serializable;

public class Servico implements Serializable {
    private Integer id;
    private String nome;
    private Float preco;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }
}
