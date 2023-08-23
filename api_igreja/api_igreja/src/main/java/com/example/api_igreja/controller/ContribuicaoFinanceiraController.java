package com.example.api_igreja.controller;

import com.example.api_igreja.domain.dto.contribuicaoFinanceira.ContribuicaoFinanceiraRequestDTO;
import com.example.api_igreja.domain.dto.contribuicaoFinanceira.ContribuicaoFinanceiraResponseDTO;
import com.example.api_igreja.domain.service.ContribuicaoFinanceiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contribuicoes")
public class ContribuicaoFinanceiraController {

    @Autowired
    private ContribuicaoFinanceiraService contribuicaoFinanceiraService;

    @PostMapping
    public ResponseEntity<ContribuicaoFinanceiraResponseDTO> cadastrarContribuicao(@RequestBody ContribuicaoFinanceiraRequestDTO requestDTO) {
        ContribuicaoFinanceiraResponseDTO responseDTO = contribuicaoFinanceiraService.cadastrar(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContribuicaoFinanceiraResponseDTO> obterContribuicaoPorId(@PathVariable Long id) {
        ContribuicaoFinanceiraResponseDTO responseDTO = contribuicaoFinanceiraService.obterPorId(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContribuicaoFinanceiraResponseDTO> atualizarContribuicao(@PathVariable Long id, @RequestBody ContribuicaoFinanceiraRequestDTO requestDTO) {
        ContribuicaoFinanceiraResponseDTO responseDTO = contribuicaoFinanceiraService.atualizar(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarContribuicao(@PathVariable Long id) {
        contribuicaoFinanceiraService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}