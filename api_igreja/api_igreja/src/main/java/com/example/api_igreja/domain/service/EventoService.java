package com.example.api_igreja.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.example.api_igreja.domain.dto.evento.EventoRequestDTO;
import com.example.api_igreja.domain.dto.evento.EventoResponseDTO;
import com.example.api_igreja.domain.exception.ResourceNotFoundException;
import com.example.api_igreja.domain.model.Evento;
import com.example.api_igreja.domain.model.Fiel;
import com.example.api_igreja.domain.repository.EventoRepository;

@Service
public class EventoService implements ICRUDService<EventoRequestDTO, EventoResponseDTO> {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public EventoResponseDTO cadastrar(EventoRequestDTO requestDTO) {
        Evento evento = new Evento();
        BeanUtils.copyProperties(requestDTO, evento);

        Evento savedEvento = eventoRepository.save(evento);
        
        EventoResponseDTO responseDTO = new EventoResponseDTO();
        BeanUtils.copyProperties(savedEvento, responseDTO);

        return responseDTO;
    }

    @Override
    public List<EventoResponseDTO> obterTodos() {
      Fiel usuario = (Fiel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      List<Evento> cf = eventoRepository.findByUsuario(usuario);
      return cf.stream().map(titulo -> mapper.map(titulo, EventoResponseDTO.class)).collect(Collectors.toList());    
    }

    public EventoResponseDTO obterPorId(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com o ID: " + id));

        EventoResponseDTO responseDTO = new EventoResponseDTO();
        BeanUtils.copyProperties(evento, responseDTO);

        return responseDTO;
    }

    @Override
    public EventoResponseDTO atualizar(Long id, EventoRequestDTO requestDTO) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com o ID: " + id));
        
        BeanUtils.copyProperties(requestDTO, evento);

        Evento updatedEvento = eventoRepository.save(evento);

        EventoResponseDTO responseDTO = new EventoResponseDTO();
        BeanUtils.copyProperties(updatedEvento, responseDTO);

        return responseDTO;
    }

    public void deletar(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Evento não encontrado com o ID: " + id);
        }
        eventoRepository.deleteById(id);
    }
}

