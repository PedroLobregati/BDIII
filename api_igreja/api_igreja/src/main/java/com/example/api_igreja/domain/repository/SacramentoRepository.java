package com.example.api_igreja.domain.repository;

import com.example.api_igreja.domain.model.Fiel;
import com.example.api_igreja.domain.model.Sacramento;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface SacramentoRepository extends JpaRepository<Sacramento, Long> {

    Optional<Sacramento> findById(@Param("id") Long id);

    boolean existsById(@Param("id") Long id);

    void deleteById(@Param("id") Long id);

    List<Sacramento> findByUsuario(Fiel usuario);   

}
