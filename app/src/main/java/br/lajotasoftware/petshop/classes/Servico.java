package br.lajotasoftware.petshop.classes;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Servico {

    public String id;
    public String nome;
    public String preco;

    public Servico() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Servico(String nome, String preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}

/*
public class Servico implements Serializable {
    private Integer id;
    private String nome;
    private String preco;

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
}*/
