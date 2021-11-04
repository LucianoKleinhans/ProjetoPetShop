package br.lajotasoftware.petshop.classes;

import java.io.Serializable;

public class CadServico implements Serializable {
    private Integer id;
    private Integer iddono;
    private String nomedono;
    private Integer idpet;
    private String nomepet;
    private Servico servico;
    private Float subtotal;
    private Float desconto;
    private Float total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIddono() {
        return iddono;
    }

    public void setIddono(Integer iddono) {
        this.iddono = iddono;
    }

    public String getNomedono() {
        return nomedono;
    }

    public void setNomedono(String nomedono) {
        this.nomedono = nomedono;
    }

    public Integer getIdpet() {
        return idpet;
    }

    public void setIdpet(Integer idpet) {
        this.idpet = idpet;
    }

    public String getNomepet() {
        return nomepet;
    }

    public void setNomepet(String nomepet) {
        this.nomepet = nomepet;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    public Float getDesconto() {
        return desconto;
    }

    public void setDesconto(Float desconto) {
        this.desconto = desconto;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
