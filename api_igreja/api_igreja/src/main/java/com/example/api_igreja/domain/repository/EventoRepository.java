package com.example.api_igreja.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.api_igreja.domain.model.Evento;
import com.example.api_igreja.domain.model.Fiel;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    Optional<Evento> findById(@Param("id") Long id);

    boolean existsById(@Param("id") Long id);

    void deleteById(@Param("id") Long id);

    List<Evento> findByUsuario(Fiel usuario);  
}
