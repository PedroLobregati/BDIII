package com.example.api_igreja.domain.dto.contribuicaoFinanceira;

import com.example.api_igreja.domain.dto.fiel.FielResponseDTO;

public class ContribuicaoFinanceiraResponseDTO {
    private Long id;
    private FielResponseDTO fielContribuinte;
    private String dataContribuicao;
    private Double valor;
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FielResponseDTO getFielContribuinte() {
        return fielContribuinte;
    }

    public void setFielContribuinte(FielResponseDTO fielContribuinte) {
        this.fielContribuinte = fielContribuinte;
    }

    public String getDataContribuicao() {
        return dataContribuicao;
    }

    public void setDataContribuicao(String dataContribuicao) {
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
