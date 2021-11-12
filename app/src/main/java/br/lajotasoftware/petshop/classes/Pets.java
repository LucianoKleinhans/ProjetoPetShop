package br.lajotasoftware.petshop.classes;

import java.io.Serializable;

public class Pets implements Serializable {
    private Integer id;
    private String nome;
    private String especie;
    private String raca;
    private String datanascimento;
    private String observacao;

    public Pets(Integer id) {
        this.id = id;
    }

    public Pets() {
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

    public String getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(String datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /*@Override
    public String toString() {
        return nome + especie + raca;
    }*/

}
