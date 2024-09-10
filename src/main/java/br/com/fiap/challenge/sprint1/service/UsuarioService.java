package br.com.fiap.challenge.sprint1.service;

import br.com.fiap.challenge.sprint1.model.cliente.Cliente;
import br.com.fiap.challenge.sprint1.model.cliente.dto.CadastrarClienteDTO;
import br.com.fiap.challenge.sprint1.model.dto.LoginDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.DetalhesPesquisaDTO;
import br.com.fiap.challenge.sprint1.model.usuario.Usuario;
import br.com.fiap.challenge.sprint1.model.usuario.dto.DetalhesUsuarioDTO;
import br.com.fiap.challenge.sprint1.model.usuario.dto.ListagemUsuarioDTO;
import br.com.fiap.challenge.sprint1.model.usuario.dto.PublicListaUsuarioDTO;
import br.com.fiap.challenge.sprint1.repository.ClienteRepository;
import br.com.fiap.challenge.sprint1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String login(LoginDTO dto){
         var token = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
         var auth = manager.authenticate(token);
         var tokenJwt = tokenService.criarToken((Usuario) auth.getPrincipal());
         return tokenJwt;
    }

    public Usuario detalhes(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return (Usuario) auth.getPrincipal();
    }

    public Page<PublicListaUsuarioDTO> listar(Pageable pageable){
        var usuario = usuarioRepository.findAll(pageable).map(PublicListaUsuarioDTO::new);
        return usuario;
    }

}
