package br.com.fiap.challenge.sprint1.controller;

import br.com.fiap.challenge.sprint1.model.endereco.logradouro.Logradouro;
import br.com.fiap.challenge.sprint1.model.endereco.logradouro.dto.AtualizarLogradouroDTO;
import br.com.fiap.challenge.sprint1.model.endereco.logradouro.dto.CriarLogradouroDTO;
import br.com.fiap.challenge.sprint1.model.endereco.logradouro.dto.DetalhesLogradouroDTO;
import br.com.fiap.challenge.sprint1.model.figuraPublica.FiguraPublica;
import br.com.fiap.challenge.sprint1.model.figuraPublica.dto.AtualizarFiguraDTO;
import br.com.fiap.challenge.sprint1.model.figuraPublica.dto.CriarFiguraDTO;
import br.com.fiap.challenge.sprint1.model.figuraPublica.dto.DetalhesFiguraDTO;
import br.com.fiap.challenge.sprint1.repository.LogradouroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/logradouro")
public class LogradouroController {

    @Autowired
    private LogradouroRepository repository;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<DetalhesLogradouroDTO> cadastrar(@RequestBody @Valid CriarLogradouroDTO dto, UriComponentsBuilder builder){
        var logradouro = new Logradouro(dto);
        repository.save(logradouro);
        var uri = builder.path("/{id}").buildAndExpand(logradouro.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesLogradouroDTO(logradouro));
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DetalhesLogradouroDTO>> listar(Pageable pageable){
        var page = repository.findAll(pageable).map(DetalhesLogradouroDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity<DetalhesLogradouroDTO> atualizar(@RequestBody @Valid AtualizarLogradouroDTO dto){
        var logradouro = repository.getReferenceById(dto.codigo());
        logradouro.atualizar(dto);
        return ResponseEntity.ok().body(new DetalhesLogradouroDTO(logradouro));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DetalhesLogradouroDTO> buscarPorId(@PathVariable Long id){
        var logradouro = repository.getReferenceById(id);
        return ResponseEntity.ok().body(new DetalhesLogradouroDTO(logradouro));
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        var logradouro = repository.getReferenceById(id);
        repository.deleteById(logradouro.getCodigo());
        return ResponseEntity.noContent().build();
    }

}
