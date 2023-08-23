package com.example.api_igreja.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.api_igreja.domain.dto.sacramento.SacramentoRequestDTO;
import com.example.api_igreja.domain.dto.sacramento.SacramentoResponseDTO;
import com.example.api_igreja.domain.exception.ResourceNotFoundException;
import com.example.api_igreja.domain.model.Fiel;
import com.example.api_igreja.domain.model.Sacramento;
import com.example.api_igreja.domain.repository.SacramentoRepository;

@Service
public class SacramentoService implements ICRUDService<SacramentoRequestDTO, SacramentoResponseDTO>{

    @Autowired
    private SacramentoRepository sacramentoRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public SacramentoResponseDTO cadastrar(SacramentoRequestDTO requestDTO) {
        Sacramento sacramento = new Sacramento();
        BeanUtils.copyProperties(requestDTO, sacramento);

        Sacramento savedSacramento = sacramentoRepository.save(sacramento);

        SacramentoResponseDTO responseDTO = new SacramentoResponseDTO();
        BeanUtils.copyProperties(savedSacramento, responseDTO);

        return responseDTO;
    }

    @Override
    public List<SacramentoResponseDTO> obterTodos() {
        Fiel usuario = (Fiel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Sacramento> lista = sacramentoRepository.findByUsuario(usuario);
        return lista.stream().map(sacramento -> mapper.map(sacramento, SacramentoResponseDTO.class))
        .collect(Collectors.toList());
    }

    public SacramentoResponseDTO obterPorId(Long id) {
        Sacramento sacramento = sacramentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sacramento não encontrado com o ID: " + id));

        SacramentoResponseDTO responseDTO = new SacramentoResponseDTO();
        BeanUtils.copyProperties(sacramento, responseDTO);

        return responseDTO;
    }

    @Override
    public SacramentoResponseDTO atualizar(Long id, SacramentoRequestDTO requestDTO) {
        Sacramento sacramento = sacramentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sacramento não encontrado com o ID: " + id));

        BeanUtils.copyProperties(requestDTO, sacramento);

        Sacramento updatedSacramento = sacramentoRepository.save(sacramento);

        SacramentoResponseDTO responseDTO = new SacramentoResponseDTO();
        BeanUtils.copyProperties(updatedSacramento, responseDTO);

        return responseDTO;
    }

    public void deletar(Long id) {
        if (!sacramentoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sacramento não encontrado com o ID: " + id);
        }
        sacramentoRepository.deleteById(id);
    }

}

