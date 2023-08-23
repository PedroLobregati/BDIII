package com.example.api_igreja.domain.dto.fiel;

public class LoginResponseDTO {
    private String token;
    private FielResponseDTO usuario;

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public FielResponseDTO getUsuario() {
        return usuario;
    }
    public void setUsuario(FielResponseDTO usuario) {
    this.usuario = usuario;
}
}
