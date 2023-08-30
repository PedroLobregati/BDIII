package com.example.api_igreja.domain.service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.api_igreja.domain.dto.sacramento.SacramentoRequestDTO;
import com.example.api_igreja.domain.dto.sacramento.SacramentoResponseDTO;
import com.example.api_igreja.domain.exception.BadRequestException;
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
    public SacramentoResponseDTO cadastrar(SacramentoRequestDTO dto) {
        Sacramento sacramento = mapper.map(dto, Sacramento.class);
        Fiel fiel = (Fiel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        sacramento.setFiel(fiel);
        sacramento.setId(null);
        sacramento = sacramentoRepository.save(sacramento);
        return mapper.map(sacramento, SacramentoResponseDTO.class);
    }

    @Override
    public List<SacramentoResponseDTO> obterTodos() {
        Fiel usuario = (Fiel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Sacramento> lista = sacramentoRepository.findByFiel(usuario);
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
    public SacramentoResponseDTO atualizar(Long id, SacramentoRequestDTO dto) {
        obterPorId(id);
        validarSacramento(dto);
        Sacramento sacramento = mapper.map(dto, Sacramento.class);
        Fiel usuario = (Fiel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        sacramento.setFiel(usuario);
        sacramento.setId(id);
        sacramento = sacramentoRepository.save(sacramento);
        return mapper.map(sacramento, SacramentoResponseDTO.class);
    }

    private void validarSacramento(SacramentoRequestDTO dto){
        if(dto.getTipoSacramento() == null || dto.getData() == null ||
        dto.getHora() == null || dto.getSacerdoteCelebrante() == null){
            throw new BadRequestException("Sacramento inválido - Campos Obrigatórios!");
        }
    }


    public void deletar(Long id) {
        if (!sacramentoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sacramento não encontrado com o ID: " + id);
        }
        sacramentoRepository.deleteById(id);
    }

}

