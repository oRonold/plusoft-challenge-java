package br.com.fiap.challenge.sprint1.controller;

import br.com.fiap.challenge.sprint1.model.dto.LoginDTO;
import br.com.fiap.challenge.sprint1.model.dto.TokenJwtDTO;
import br.com.fiap.challenge.sprint1.model.usuario.dto.DetalhesUsuarioDTO;
import br.com.fiap.challenge.sprint1.model.usuario.dto.ListagemUsuarioDTO;
import br.com.fiap.challenge.sprint1.model.usuario.dto.PublicListaUsuarioDTO;
import br.com.fiap.challenge.sprint1.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/login")
    public ResponseEntity<TokenJwtDTO> login(@RequestBody @Valid LoginDTO dto){
        var token = service.login(dto);
        return ResponseEntity.ok(new TokenJwtDTO(token));
    }

    @GetMapping("/publico/listar")
    public ResponseEntity<Page<PublicListaUsuarioDTO>> listar(Pageable pageable){
        var page = service.listar(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/detalhes")
    public ResponseEntity<DetalhesUsuarioDTO> detalhes(){
        var usuario = service.detalhes();
        return ResponseEntity.ok(new DetalhesUsuarioDTO(usuario));
    }

}
