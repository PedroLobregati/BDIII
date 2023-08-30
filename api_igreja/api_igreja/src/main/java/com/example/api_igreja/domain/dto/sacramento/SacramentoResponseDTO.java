package com.example.api_igreja.domain.dto.sacramento;

import com.example.api_igreja.domain.Enum.EtipoSacramento;

public class SacramentoResponseDTO {
    private Long id;
    private EtipoSacramento tipoSacramento;
    private String data;
    private String hora;
    private String sacerdoteCelebrante;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public EtipoSacramento getTipoSacramento() {
        return tipoSacramento;
    }
    public void setTipoSacramento(EtipoSacramento tipoSacramento) {
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
    public String getSacerdoteCelebrante() {
        return sacerdoteCelebrante;
    }
    public void setSacerdoteCelebrante(String sacerdoteCelebrante) {
        this.sacerdoteCelebrante = sacerdoteCelebrante;
    }
}
