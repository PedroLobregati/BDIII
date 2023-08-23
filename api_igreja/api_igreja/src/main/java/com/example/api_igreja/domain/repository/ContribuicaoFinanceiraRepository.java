package com.example.api_igreja.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api_igreja.domain.model.ContribuicaoFinanceira;
import com.example.api_igreja.domain.model.Fiel;

public interface ContribuicaoFinanceiraRepository extends JpaRepository<ContribuicaoFinanceira, Long>{
    
    List<ContribuicaoFinanceira> findByUsuario(Fiel fiel); 
}
