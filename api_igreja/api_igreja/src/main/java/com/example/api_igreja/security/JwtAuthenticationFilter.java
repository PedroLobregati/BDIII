package com.example.api_igreja.security;
import java.io.IOException;
import java.util.Date;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.api_igreja.common.ConversorData;
import com.example.api_igreja.domain.model.ErroResposta;
import com.example.api_igreja.domain.model.Fiel;
import com.example.api_igreja.domain.dto.fiel.LoginRequestDTO;
import com.example.api_igreja.domain.dto.fiel.LoginResponseDTO;
import com.example.api_igreja.domain.dto.fiel.FielResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
 // @Autowired
 // private ModelMapper mapper;
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil){
        super();
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/auth");
    }
     @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        try{
            LoginRequestDTO login = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDTO.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha());
            Authentication auth = authenticationManager.authenticate(authToken);
        return auth; //valida o cadastro, rpocura o usuario pelo email e valida se a senha for a mesma do banco de dados
        }catch(BadCredentialsException e){
            throw new BadCredentialsException("Usuário ou senha Inválidos");
        }catch(Exception e){
            throw new InternalAuthenticationServiceException(e.getMessage());
        }
    }
    @Override
    //Usuario e senha validados
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException{
        Fiel fiel = (Fiel) authResult.getPrincipal();
        String token = jwtUtil.gerarToken(authResult);
        // Alteração - FielResponseDTODTO FielResponseDTO =
        //mapper.map(usuario, FielResponseDTODTO.class);
        FielResponseDTO FielResponseDTO = new FielResponseDTO();
        FielResponseDTO.setId(fiel.getId());
        FielResponseDTO.setNome(fiel.getUsername());
        FielResponseDTO.setDataNascimento(fiel.getDataNascimento());
        FielResponseDTO.setDataCadastro(fiel.getDataCadastro());
        FielResponseDTO.setDataInativacao(fiel.getDataInativacao());
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setToken("Bearer " + token);
        loginResponseDTO.setUsuario(FielResponseDTO);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(loginResponseDTO));
    }
    @Override
    //Usuario e senha nao validdos
    protected void unsuccessfulAuthentication(HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException{
            String dataHora = ConversorData.converterDateParaDataHora(new Date());
            ErroResposta erro = new ErroResposta(dataHora, 401, "Unauthorized",
            failed.getMessage());
            response.setStatus(401); //nao ta autorizado a entrar
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(erro));
        }
    }
