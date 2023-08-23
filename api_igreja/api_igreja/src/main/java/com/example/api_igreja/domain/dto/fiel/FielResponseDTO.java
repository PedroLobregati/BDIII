package com.example.api_igreja.domain.dto.fiel;

import java.util.Date;
import java.util.List;

import com.example.api_igreja.domain.dto.endereco.EnderecoResponseDTO;
import com.example.api_igreja.domain.dto.sacramento.SacramentoResponseDTO;

public class FielResponseDTO {
    private Long id;
    private String nome;
    private Date dataNascimento;
    private EnderecoResponseDTO endereco;
    private String senha;
    private String email;
    private List<SacramentoResponseDTO> sacramentosRealizados;
    private Date dataCadastro;
    private Date dataInativacao;
    
    public Date getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    public Date getDataInativacao() {
        return dataInativacao;
    }
    public void setDataInativacao(Date dataInativacao) {
        this.dataInativacao = dataInativacao;
    }
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
    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public EnderecoResponseDTO getEndereco() {
        return endereco;
    }
    public void setEndereco(EnderecoResponseDTO endereco) {
        this.endereco = endereco;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<SacramentoResponseDTO> getSacramentosRealizados() {
        return sacramentosRealizados;
    }
    public void setSacramentosRealizados(List<SacramentoResponseDTO> sacramentosRealizados) {
        this.sacramentosRealizados = sacramentosRealizados;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
