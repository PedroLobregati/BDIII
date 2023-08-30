package com.example.api_igreja.domain.model;

import java.util.Date;

import com.example.api_igreja.domain.Enum.EtipoSacramento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Sacramento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sacramento")
    private Long id;

    @Enumerated(EnumType.STRING)
    
    private EtipoSacramento tipoSacramento;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date data;

    @Column(nullable = false)
    private String hora;

    @ManyToOne
    @JoinColumn(name = "id_fiel")
    private Fiel fiel;

    private String sacerdoteCelebrante;


    public Sacramento() {
    }

    public Sacramento(EtipoSacramento tipoSacramento, Date data, String hora, Fiel fiel,
                      String Celebrante) {
        this.tipoSacramento = tipoSacramento;
        this.data = data;
        this.hora = hora;
        this.fiel = fiel;
        this.sacerdoteCelebrante = Celebrante;
    }

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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Fiel getFiel() {
        return fiel;
    }

    public void setFiel(Fiel fiel) {
        this.fiel = fiel;
    }

    public String  getSacerdoteCelebrante() {
        return sacerdoteCelebrante;
    }

    public void setSacerdoteCelebrante(String Celebrante) {
        this.sacerdoteCelebrante = Celebrante;
    }
}
