package br.com.fiap.challenge.sprint1.controller;

import br.com.fiap.challenge.sprint1.model.cliente.dto.CadastrarClienteDTO;
import br.com.fiap.challenge.sprint1.model.usuario.Usuario;
import br.com.fiap.challenge.sprint1.model.usuario.dto.AtualizarUsuarioDTO;
import br.com.fiap.challenge.sprint1.model.usuario.dto.DetalhesUsuarioDTO;
import br.com.fiap.challenge.sprint1.repository.PesquisaRepository;
import br.com.fiap.challenge.sprint1.repository.UsuarioRepository;
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

    @GetMapping
    public ResponseEntity<Page<DetalhesUsuarioDTO>> listar(Pageable pageable){
        var page = usuarioRepository.findAll(pageable).map(DetalhesUsuarioDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesUsuarioDTO> detalhesUsuario(@PathVariable Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok().body(new DetalhesUsuarioDTO(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesUsuarioDTO> buscarPorId(@PathVariable Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok().body(new DetalhesUsuarioDTO(usuario));
    }

}
