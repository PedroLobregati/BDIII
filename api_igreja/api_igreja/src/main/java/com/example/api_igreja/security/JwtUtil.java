package com.example.api_igreja.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.api_igreja.domain.model.Fiel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    @Value("${auth.jwt.secret}")
    private String jwtsecret;
    @Value("${auth.jwt-experiation-milliseg}")
    private Long jwtExpirationMiliseg;

    public String gerarToken(Authentication authencation){
        Date dataExpiracao = new Date(new Date().getTime() + jwtExpirationMiliseg);
        Fiel usuario = (Fiel) authencation.getPrincipal();
    
        try{
            Key secretKey = Keys.hmacShaKeyFor(jwtsecret.getBytes("UTF-8"));
    
            return Jwts.builder()
                .setSubject(usuario.getEmail()) 
                .setIssuedAt(new Date())
                .setExpiration(dataExpiracao)
                .signWith(secretKey)
                .compact();
        } catch(Exception e){
            return "";
        }
    }
    

    private Claims getClaims(String token){
        try{
            Key secretKey = Keys.hmacShaKeyFor(jwtsecret.getBytes("UTF-8"));
            Claims claims = Jwts.parserBuilder()
            .setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
            return claims;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public String getUserName(String token){
        Claims claims = getClaims(token);
        if(claims == null){
            return "";
        }
        return claims.getSubject();
    }


    public boolean isValidToken(String token){
        Claims claims = getClaims(token);
        if(claims == null){
            return false;
        }
        String email = claims.getSubject();
        Date dataExpiracao = claims.getExpiration();
        Date agora = new Date(System.currentTimeMillis());// pega a data de agora e verifica se é antes da data de expiracao
        if((email != null) && (agora.before(dataExpiracao))){
            return true;
        }
        return false;
    }
}