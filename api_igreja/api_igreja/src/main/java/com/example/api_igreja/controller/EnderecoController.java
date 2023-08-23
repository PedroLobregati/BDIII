package com.example.api_igreja.controller;

import com.example.api_igreja.domain.dto.endereco.EnderecoRequestDTO;
import com.example.api_igreja.domain.dto.endereco.EnderecoResponseDTO;
import com.example.api_igreja.domain.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> cadastrarEndereco(@RequestBody EnderecoRequestDTO requestDTO) {
        EnderecoResponseDTO responseDTO = enderecoService.cadastrarEndereco(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}