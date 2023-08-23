package com.example.api_igreja.domain.repository;
import com.example.api_igreja.domain.model.Fiel;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FielRepository extends JpaRepository<Fiel, Long> {
    Optional<Fiel> findByEmail(String email);  
}
