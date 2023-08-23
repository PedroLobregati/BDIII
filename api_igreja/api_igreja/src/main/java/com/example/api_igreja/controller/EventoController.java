package com.example.api_igreja.controller;

import com.example.api_igreja.domain.dto.evento.EventoRequestDTO;
import com.example.api_igreja.domain.dto.evento.EventoResponseDTO;
import com.example.api_igreja.domain.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping
    public ResponseEntity<EventoResponseDTO> cadastrarEvento(@RequestBody EventoRequestDTO requestDTO) {
        EventoResponseDTO responseDTO = eventoService.cadastrar(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> obterEventoPorId(@PathVariable Long id) {
        EventoResponseDTO responseDTO = eventoService.obterPorId(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> atualizarEvento(@PathVariable Long id, @RequestBody EventoRequestDTO requestDTO) {
        EventoResponseDTO responseDTO = eventoService.atualizar(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEvento(@PathVariable Long id) {
        eventoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}