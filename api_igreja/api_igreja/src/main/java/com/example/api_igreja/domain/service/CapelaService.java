package com.example.api_igreja.domain.service;

import com.example.api_igreja.domain.dto.capela.CapelaRequestDTO;
import com.example.api_igreja.domain.dto.capela.CapelaResponseDTO;
import com.example.api_igreja.domain.exception.ResourceNotFoundException;
import com.example.api_igreja.domain.model.Capela;
import com.example.api_igreja.domain.repository.CapelaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CapelaService implements ICRUDService<CapelaRequestDTO, CapelaResponseDTO>{

    @Autowired
    private CapelaRepository capelaRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CapelaResponseDTO cadastrar(CapelaRequestDTO requestDTO) {
        Capela capela = new Capela();
        BeanUtils.copyProperties(requestDTO, capela);

        Capela savedCapela = capelaRepository.save(capela);

        CapelaResponseDTO responseDTO = new CapelaResponseDTO();
        BeanUtils.copyProperties(savedCapela, responseDTO);

        return responseDTO;
    }

    @Override
    public List<CapelaResponseDTO> obterTodos() {
        List<Capela> capelas = capelaRepository.findAll();
        return capelas.stream()
        .map(usuario -> mapper.map(usuario, CapelaResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CapelaResponseDTO obterPorId(Long id) {
        Optional<Capela> optTitulo = capelaRepository.findById(id);
        if(optTitulo.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível encontrar" +
            "a capela com o id: " + id);
        }
        return mapper.map(optTitulo.get(), CapelaResponseDTO.class);
    }

    @Override
    public CapelaResponseDTO atualizar(Long id, CapelaRequestDTO dto) {
        Capela capela = capelaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Capela não encontrada com o ID: " + id));
        
        BeanUtils.copyProperties(dto, capela);

        Capela updatedEvento = capelaRepository.save(capela);

        CapelaResponseDTO responseDTO = new CapelaResponseDTO();
        BeanUtils.copyProperties(updatedEvento, responseDTO);

        return responseDTO;
    }

    @Override
    public void deletar(Long id) {
        if (!capelaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Evento não encontrado com o ID: " + id);
        }
        capelaRepository.deleteById(id);
    }

    
}
