package com.example.api_igreja.domain.dto.sacramento;

import com.example.api_igreja.domain.dto.capela.CapelaResponseDTO;
import com.example.api_igreja.domain.dto.fiel.FielResponseDTO;

public class SacramentoRequestDTO {
    private Long id;
    private String tipoSacramento;
    private String data;
    private String hora;
    private FielResponseDTO fiel;
    private String sacerdoteCelebrante;
    private CapelaResponseDTO capelaLocal;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTipoSacramento() {
        return tipoSacramento;
    }
    public void setTipoSacramento(String tipoSacramento) {
        this.tipoSacramento = tipoSacramento;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
    public FielResponseDTO getFiel() {
        return fiel;
    }
    public void setFiel(FielResponseDTO fiel) {
        this.fiel = fiel;
    }
    public String getSacerdoteCelebrante() {
        return sacerdoteCelebrante;
    }
    public void setSacerdoteCelebrante(String sacerdoteCelebrante) {
        this.sacerdoteCelebrante = sacerdoteCelebrante;
    }
    public CapelaResponseDTO getCapelaLocal() {
        return capelaLocal;
    }
    public void setCapelaLocal(CapelaResponseDTO capelaLocal) {
        this.capelaLocal = capelaLocal;
    }
}
