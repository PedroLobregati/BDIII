package com.example.api_igreja.domain.repository;

import com.example.api_igreja.domain.model.Endereco;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findById(@Param("id") Long id);

    boolean existsById(@Param("id") Long id);

    void deleteById(@Param("id") Long id);
}