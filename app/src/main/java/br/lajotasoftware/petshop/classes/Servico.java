package br.lajotasoftware.petshop.classes;

import java.io.Serializable;

public class Servico implements Serializable {
    private Integer id;
    private String nome;
    private String preco;

    public Servico(Integer id) {
        this.id = id;
    }

    public Servico() {
    }

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

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        {
            return nome+ " - "+"R$"+preco;
        }
    }
}
