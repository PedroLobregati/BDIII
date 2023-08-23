package com.example.api_igreja.domain.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

@Entity
@Table(name = "contribuicoes")
public class ContribuicaoFinanceira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contribuicao")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_fiel")
    private Fiel fielContribuinte;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_contribuicao", nullable = false)
    private Date dataContribuicao;

    @Column(nullable = false)
    private Double valor;

    private String descricao;

    public ContribuicaoFinanceira() {
    }

    public ContribuicaoFinanceira(Fiel fielContribuinte, Date dataContribuicao, Double valor, String descricao) {
        this.fielContribuinte = fielContribuinte;
        this.dataContribuicao = dataContribuicao;
        this.valor = valor;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Fiel getFielContribuinte() {
        return fielContribuinte;
    }

    public void setFielContribuinte(Fiel fielContribuinte) {
        this.fielContribuinte = fielContribuinte;
    }

    public Date getDataContribuicao() {
        return dataContribuicao;
    }

    public void setDataContribuicao(Date dataContribuicao) {
        this.dataContribuicao = dataContribuicao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
