package com.example.api_igreja.domain.Enum;

public enum EtipoSacramento {
    BATISMO("Batismo"),
    CASAMENTO("Casamento"),
    CONFISSAO("Confissao"),
    CRISMA("Crisma");

    private String valor;
    private EtipoSacramento(String valor){
        this.valor = valor;
    }

    public String getValor(){
        return valor;
    }

}
