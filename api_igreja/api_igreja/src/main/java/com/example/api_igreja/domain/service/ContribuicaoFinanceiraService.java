package com.example.api_igreja.domain.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.api_igreja.domain.dto.contribuicaoFinanceira.ContribuicaoFinanceiraRequestDTO;
import com.example.api_igreja.domain.dto.contribuicaoFinanceira.ContribuicaoFinanceiraResponseDTO;
import com.example.api_igreja.domain.exception.ResourceNotFoundException;
import com.example.api_igreja.domain.model.ContribuicaoFinanceira;
import com.example.api_igreja.domain.model.Fiel;
import com.example.api_igreja.domain.repository.ContribuicaoFinanceiraRepository;

@Service
public class ContribuicaoFinanceiraService implements ICRUDService<ContribuicaoFinanceiraRequestDTO, ContribuicaoFinanceiraResponseDTO> {

    @Autowired
    private ContribuicaoFinanceiraRepository contribuicaoFinanceiraRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ContribuicaoFinanceiraResponseDTO cadastrar(ContribuicaoFinanceiraRequestDTO dto) {
        ContribuicaoFinanceira cf = mapper.map(dto, ContribuicaoFinanceira.class);
        Fiel usuario = (Fiel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cf.setFielContribuinte(usuario);
        cf.setId(null);
        cf = contribuicaoFinanceiraRepository.save(cf);
        return mapper.map(cf, ContribuicaoFinanceiraResponseDTO.class);
    }

    @Override
    public List<ContribuicaoFinanceiraResponseDTO> obterTodos() {
      Fiel usuario = (Fiel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      List<ContribuicaoFinanceira> cf = contribuicaoFinanceiraRepository.findByUsuario(usuario);
      return cf.stream().map(titulo -> mapper.map(titulo, ContribuicaoFinanceiraResponseDTO.class)).collect(Collectors.toList());    
    }

    public ContribuicaoFinanceiraResponseDTO obterPorId(Long id) {
        Optional<ContribuicaoFinanceira> optTitulo = contribuicaoFinanceiraRepository.findById(id);
        if(optTitulo.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível encontrar" +
            "a contribuição com o id: " + id);
        }
        return mapper.map(optTitulo.get(), ContribuicaoFinanceiraResponseDTO.class);
    }

    @Override
    public ContribuicaoFinanceiraResponseDTO atualizar(Long id, ContribuicaoFinanceiraRequestDTO dto) {
        obterPorId(id);
        ContribuicaoFinanceira cf = mapper.map(dto, ContribuicaoFinanceira.class);
        Fiel usuario = (Fiel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cf.setFielContribuinte(usuario);
        cf.setId(id);
        cf = contribuicaoFinanceiraRepository.save(cf);
        return mapper.map(cf, ContribuicaoFinanceiraResponseDTO.class);
    }

    @Override
    public void deletar(Long id) {
        obterPorId(id);
        contribuicaoFinanceiraRepository.deleteById(id);
    }
}
