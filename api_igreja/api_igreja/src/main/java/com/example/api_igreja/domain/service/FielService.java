package com.example.api_igreja.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.api_igreja.domain.dto.fiel.FielRequestDTO;
import com.example.api_igreja.domain.dto.fiel.FielResponseDTO;
import com.example.api_igreja.domain.exception.BadRequestException;
import com.example.api_igreja.domain.exception.ResourceNotFoundException;
import com.example.api_igreja.domain.model.Fiel;
import com.example.api_igreja.domain.repository.FielRepository;

@Service
public class FielService implements ICRUDService<FielRequestDTO, FielResponseDTO>{

   @Autowired
    private FielRepository fielRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired 
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<FielResponseDTO> obterTodos() {
        List<Fiel> usuarios = fielRepository.findAll();
        return usuarios.stream()
        .map(usuario -> mapper.map(usuario, FielResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public FielResponseDTO obterPorId(Long id) {
        Optional<Fiel> optUsuario = fielRepository.findById(id);
        if(optUsuario.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível encontrar o id: " + id);
        }
        return mapper.map(optUsuario.get(), FielResponseDTO.class);
    }

    @Override
    public FielResponseDTO cadastrar(FielRequestDTO dto) {
        if (dto.getEmail() == null || dto.getSenha() == null){
            throw new BadRequestException("Email e Senha são obrigatórios");
        }
        System.out.println("-------------ID-----------  " + dto.getId());
        Optional <Fiel> optUsuario = fielRepository.findByEmail(dto.getEmail());
        if (optUsuario.isPresent()){
            throw new BadRequestException("Já existe usuário com esse email: " + dto.getEmail());
        }
        Fiel fiel = mapper.map(dto, Fiel.class);
        fiel.setDataCadastro(new Date());
        String senha = passwordEncoder.encode(fiel.getSenha());
        fiel.setSenha(senha);
        fiel.setId(null);
        fiel = fielRepository.save(fiel);
        return mapper.map(fiel,FielResponseDTO.class);
    }

    @Override
    public FielResponseDTO atualizar(Long id, FielRequestDTO dto) {
        FielResponseDTO fielResponseDTO = obterPorId(id);
        if (dto.getEmail() == null || dto.getSenha() == null){
            throw new BadRequestException("Email e Senha são obrigatórios");
        }
        Fiel fiel = mapper.map(dto, Fiel.class);
        fiel.setSenha(dto.getSenha());
        fiel.setId(id);
        fiel.setDataCadastro(fielResponseDTO.getDataCadastro());
        fiel.setDataInativacao(fielResponseDTO.getDataInativacao());
        fiel = fielRepository.save(fiel);
        return mapper.map(fiel, FielResponseDTO.class);
    }

    @Override
    public void deletar(Long id) {
        obterPorId(id);
        fielRepository.deleteById(id);

        /*Optional<Fiel> optUsuario = fielRepository.findById(id);
        if(optUsuario.isEmpty()){
            throw new ResourceNotFoundException("Nao foi possível encontrar o id: " + id);
        }
        Fiel fiel = optUsuario.get();
        fiel.setDataInativacao(new Date());
        fielRepository.save(fiel);*/
    }

}
