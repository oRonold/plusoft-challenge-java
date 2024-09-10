package br.com.fiap.challenge.sprint1.controller;

import br.com.fiap.challenge.sprint1.model.cliente.dto.CadastrarClienteDTO;
import br.com.fiap.challenge.sprint1.model.cliente.dto.DetalhesClienteDTO;
import br.com.fiap.challenge.sprint1.model.dto.LoginDTO;
import br.com.fiap.challenge.sprint1.model.dto.TokenJwtDTO;
import br.com.fiap.challenge.sprint1.model.usuario.dto.DetalhesUsuarioDTO;
import br.com.fiap.challenge.sprint1.model.usuario.dto.ListagemUsuarioDTO;
import br.com.fiap.challenge.sprint1.repository.UsuarioRepository;
import br.com.fiap.challenge.sprint1.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService service;

    @PostMapping("/login")
    public ResponseEntity<TokenJwtDTO> login(@RequestBody @Valid LoginDTO dto){
        var token = service.login(dto);
        return ResponseEntity.ok(new TokenJwtDTO(token));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemUsuarioDTO>> listar(Pageable pageable){
        var page = usuarioRepository.findAll(pageable).map(ListagemUsuarioDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesUsuarioDTO> detalhesUsuario(@PathVariable Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok().body(new DetalhesUsuarioDTO(usuario));
    }

}
