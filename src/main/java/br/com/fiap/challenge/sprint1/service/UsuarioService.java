package br.com.fiap.challenge.sprint1.service;

import br.com.fiap.challenge.sprint1.model.cliente.Cliente;
import br.com.fiap.challenge.sprint1.model.cliente.dto.CadastrarClienteDTO;
import br.com.fiap.challenge.sprint1.model.dto.LoginDTO;
import br.com.fiap.challenge.sprint1.model.usuario.Usuario;
import br.com.fiap.challenge.sprint1.repository.ClienteRepository;
import br.com.fiap.challenge.sprint1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;


    public String login(LoginDTO dto){
         var token = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
         var auth = manager.authenticate(token);
         var tokenJwt = tokenService.criarToken((Usuario) auth.getPrincipal());
         return tokenJwt;
    }
}
