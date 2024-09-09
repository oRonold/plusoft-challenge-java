package br.com.fiap.challenge.sprint1.service;

import br.com.fiap.challenge.sprint1.model.dto.LoginDTO;
import br.com.fiap.challenge.sprint1.model.usuario.Usuario;
import br.com.fiap.challenge.sprint1.model.usuario.dto.CadastrarUsuarioDTO;
import br.com.fiap.challenge.sprint1.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public Usuario cadastrar(CadastrarUsuarioDTO dto){
        var usuario = new Usuario(dto, passwordEncoder.encode(dto.senha()));
        usuarioRepository.save(usuario);
        return usuario;
    }

    public String login(LoginDTO dto){
         var token = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
         var auth = manager.authenticate(token);
         var tokenJwt = tokenService.criarToken((Usuario) auth.getPrincipal());
         return tokenJwt;
    }
}
