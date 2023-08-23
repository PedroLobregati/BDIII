package com.example.api_igreja.controller;

import com.example.api_igreja.domain.dto.sacramento.SacramentoRequestDTO;
import com.example.api_igreja.domain.dto.sacramento.SacramentoResponseDTO;
import com.example.api_igreja.domain.service.SacramentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sacramentos")
public class SacramentoController {

    @Autowired
    private SacramentoService sacramentoService;

    @GetMapping
    public ResponseEntity<List<SacramentoResponseDTO>> obterTodos() {
        List<SacramentoResponseDTO> responseDTOs = sacramentoService.obterTodos();
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SacramentoResponseDTO> obterPorId(@PathVariable Long id) {
        SacramentoResponseDTO responseDTO = sacramentoService.obterPorId(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<SacramentoResponseDTO> cadastrar(@RequestBody SacramentoRequestDTO dto) {
        SacramentoResponseDTO responseDTO = sacramentoService.cadastrar(dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SacramentoResponseDTO> atualizar(@PathVariable Long id, @RequestBody SacramentoRequestDTO dto) {
        SacramentoResponseDTO responseDTO = sacramentoService.atualizar(id, dto);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        sacramentoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
