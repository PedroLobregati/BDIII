package com.example.api_igreja.controller;

import com.example.api_igreja.domain.dto.capela.CapelaRequestDTO;
import com.example.api_igreja.domain.dto.capela.CapelaResponseDTO;
import com.example.api_igreja.domain.service.CapelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/capelas")
public class CapelaController {

    @Autowired
    private CapelaService capelaService;

    @PostMapping
    public ResponseEntity<CapelaResponseDTO> cadastrarCapela(@RequestBody CapelaRequestDTO requestDTO) {
        CapelaResponseDTO responseDTO = capelaService.cadastrar(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}