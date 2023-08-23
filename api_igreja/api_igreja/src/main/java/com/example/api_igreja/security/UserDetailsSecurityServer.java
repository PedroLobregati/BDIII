package com.example.api_igreja.security;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.api_igreja.domain.model.Fiel;
import com.example.api_igreja.domain.repository.FielRepository;

@Component
public class UserDetailsSecurityServer implements UserDetailsService {
    @Autowired
    private FielRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Fiel> optUsuario = usuarioRepository.findByEmail(username);
        if(!optUsuario.isPresent()){ //isPresent = contrario do isEmpty
            throw new UsernameNotFoundException("Usuário ou senha Inválidos.");
        }
        return optUsuario.get();
    }

}