package com.example.api_igreja.domain.service;

import com.example.api_igreja.domain.dto.endereco.EnderecoRequestDTO;
import com.example.api_igreja.domain.dto.endereco.EnderecoResponseDTO;
import com.example.api_igreja.domain.model.Endereco;
import com.example.api_igreja.domain.repository.EnderecoRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional
    public EnderecoResponseDTO cadastrarEndereco(EnderecoRequestDTO requestDTO) {
        Endereco endereco = new Endereco();
        BeanUtils.copyProperties(requestDTO, endereco);

        Endereco savedEndereco = enderecoRepository.save(endereco);

        EnderecoResponseDTO responseDTO = new EnderecoResponseDTO();
        BeanUtils.copyProperties(savedEndereco, responseDTO);

        return responseDTO;
    }
}
