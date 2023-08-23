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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "sacramentos")
public class Sacramento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sacramento")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_sacramento", nullable = false)
    private EtipoSacramento tipoSacramento;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date data;

    @Column(nullable = false)
    private String hora;

    @ManyToOne
    @JoinColumn(name = "id_fiel")
    private Fiel fiel;

    @Column
    private String Celebrante;

    @ManyToOne
    @JoinColumn(name = "id_capela")
    private Capela capelaLocal;

    public Sacramento() {
    }

    public Sacramento(EtipoSacramento tipoSacramento, Date data, String hora, Fiel fiel,
                      String Celebrante, Capela capelaLocal) {
        this.tipoSacramento = tipoSacramento;
        this.data = data;
        this.hora = hora;
        this.fiel = fiel;
        this.Celebrante = Celebrante;
        this.capelaLocal = capelaLocal;
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

    public String  getCelebrante() {
        return  Celebrante;
    }

    public void setCelebrante(String Celebrante) {
        this.Celebrante = Celebrante;
    }

    public Capela getCapelaLocal() {
        return capelaLocal;
    }

    public void setCapelaLocal(Capela capelaLocal) {
        this.capelaLocal = capelaLocal;
    }
}
