package com.example.api_igreja.domain.dto.capela;

import com.example.api_igreja.domain.dto.endereco.EnderecoResponseDTO;

public class CapelaResponseDTO {
    private Long id;
    private String nome;
    private EnderecoResponseDTO endereco;
    private String telefone;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public EnderecoResponseDTO getEndereco() {
        return endereco;
    }
    public void setEndereco(EnderecoResponseDTO endereco) {
        this.endereco = endereco;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
