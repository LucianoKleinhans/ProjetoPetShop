package br.lajotasoftware.petshop.classes;

import java.io.Serializable;

public class Pets implements Serializable {
    private String id;
    private String nome;
    private String especie;
    private String raca;
    private String observacao;

    public Pets(String id) {
        this.id = id;
    }

    public Pets() {
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

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return nome +"-"+ especie;
    }

}
