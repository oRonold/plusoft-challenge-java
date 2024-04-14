package br.com.fiap.challenge.sprint1.controller;

import br.com.fiap.challenge.sprint1.model.pesquisa.Pesquisa;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.AtualizarPesquisaDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.CriarPesquisaDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.DetalhesPesquisaDTO;
import br.com.fiap.challenge.sprint1.model.usuario.Usuario;
import br.com.fiap.challenge.sprint1.model.usuario.dto.AtualizarUsuarioDTO;
import br.com.fiap.challenge.sprint1.model.usuario.dto.CriarUsuarioDTO;
import br.com.fiap.challenge.sprint1.model.usuario.dto.DetalhesUsuarioDTO;
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
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<DetalhesUsuarioDTO> criar(@RequestBody @Valid CriarUsuarioDTO dto, UriComponentsBuilder builder){
        var usuario = new Usuario(dto);
        repository.save(usuario);
        var uri = builder.path("/{id}").buildAndExpand(usuario.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesUsuarioDTO(usuario));
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DetalhesUsuarioDTO>> listar(Pageable pageable){
        var page = repository.findAll(pageable).map(DetalhesUsuarioDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity<DetalhesUsuarioDTO> atualizar(@RequestBody @Valid AtualizarUsuarioDTO dto){
        var usuario = repository.getReferenceById(dto.codigo());
        usuario.atualizar(dto);
        return ResponseEntity.ok().body(new DetalhesUsuarioDTO(usuario));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DetalhesUsuarioDTO> buscarPorId(@PathVariable Long id){
        var usuario = repository.getReferenceById(id);
        return ResponseEntity.ok().body(new DetalhesUsuarioDTO(usuario));
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        var usuario = repository.getReferenceById(id);
        repository.deleteById(usuario.getCodigo());
        return ResponseEntity.noContent().build();
    }

}
