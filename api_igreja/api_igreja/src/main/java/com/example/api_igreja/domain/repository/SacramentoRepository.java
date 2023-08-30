package com.example.api_igreja.domain.repository;

import com.example.api_igreja.domain.model.Fiel;
import com.example.api_igreja.domain.model.Sacramento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SacramentoRepository extends JpaRepository<Sacramento, Long> {
    List<Sacramento> findByFiel(Fiel fiel);   

}
