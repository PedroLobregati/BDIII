package com.example.api_igreja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_igreja.domain.dto.fiel.FielRequestDTO;
import com.example.api_igreja.domain.dto.fiel.FielResponseDTO;
import com.example.api_igreja.domain.service.FielService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/fieis")
public class FielController {
    @Autowired
    private FielService fielService;

    @GetMapping
    public ResponseEntity<List<FielResponseDTO>> obterTodos(){
        return ResponseEntity.ok(fielService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FielResponseDTO> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(fielService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<FielResponseDTO> cadastrar(@RequestBody FielRequestDTO dto){
        FielResponseDTO fiel = fielService.cadastrar(dto);
        return new ResponseEntity<FielResponseDTO>(fiel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FielResponseDTO> atualizar(@PathVariable Long id, @RequestBody FielRequestDTO dto){
       
        FielResponseDTO fiel = fielService.atualizar(id, dto);
        return new ResponseEntity<FielResponseDTO>(fiel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        fielService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
