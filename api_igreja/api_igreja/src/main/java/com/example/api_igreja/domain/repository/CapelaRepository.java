package com.example.api_igreja.domain.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api_igreja.domain.model.Capela;

public interface CapelaRepository extends JpaRepository<Capela, Long> {
    Optional<Capela> findById(Long id);
}
