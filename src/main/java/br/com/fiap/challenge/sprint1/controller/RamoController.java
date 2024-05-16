package br.com.fiap.challenge.sprint1.controller;

import br.com.fiap.challenge.sprint1.model.cliente.dto.CadastrarClienteDTO;
import br.com.fiap.challenge.sprint1.model.ramo.Ramo;
import br.com.fiap.challenge.sprint1.model.ramo.dto.AtualizarRamoDTO;
import br.com.fiap.challenge.sprint1.model.ramo.dto.DetalhesRamoDTO;
import br.com.fiap.challenge.sprint1.repository.RamoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/ramo")
public class RamoController {

    @Autowired
    private RamoRepository repository;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<DetalhesRamoDTO> criar(@RequestBody @Valid CadastrarClienteDTO dto, UriComponentsBuilder builder){
        var ramo = new Ramo(dto);
        repository.save(ramo);
        var uri = builder.path("/{id}").buildAndExpand(ramo.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesRamoDTO(ramo));
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DetalhesRamoDTO>> listar(Pageable pageable){
        var page = repository.findAll(pageable).map(DetalhesRamoDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity<DetalhesRamoDTO> atualizar(@RequestBody @Valid AtualizarRamoDTO dto){
        var ramo = repository.getReferenceById(dto.codigo());
        ramo.atualizar(dto);
        return ResponseEntity.ok().body(new DetalhesRamoDTO(ramo));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DetalhesRamoDTO> buscarPorId(@PathVariable Long id){
        var ramo = repository.getReferenceById(id);
        return ResponseEntity.ok().body(new DetalhesRamoDTO(ramo));
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        var ramo = repository.getReferenceById(id);
        repository.deleteById(ramo.getCodigo());
        return ResponseEntity.noContent().build();
    }

}
