package br.com.fiap.challenge.sprint1.controller;

import br.com.fiap.challenge.sprint1.model.cliente.dto.CadastrarClienteDTO;
import br.com.fiap.challenge.sprint1.model.endereco.pais.Pais;
import br.com.fiap.challenge.sprint1.model.endereco.pais.dto.AtualizarPaisDTO;
import br.com.fiap.challenge.sprint1.model.endereco.pais.dto.CriarPaisDTO;
import br.com.fiap.challenge.sprint1.model.endereco.pais.dto.DetalhesPaisDTO;
import br.com.fiap.challenge.sprint1.repository.PaisRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pais")
public class PaisController {

    @Autowired
    private PaisRepository repository;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<DetalhesPaisDTO> criar(@RequestBody @Valid CadastrarClienteDTO dto, UriComponentsBuilder builder){
        var pais = new Pais(dto);
        repository.save(pais);
        var uri = builder.path("/{id}").buildAndExpand(pais.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesPaisDTO(pais));
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DetalhesPaisDTO>> listar(Pageable pageable){
        var page = repository.findAll(pageable).map(DetalhesPaisDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity<DetalhesPaisDTO> atualizar(@RequestBody @Valid AtualizarPaisDTO dto){
        var pais = repository.getReferenceById(dto.codigo());
        pais.atualizar(dto);
        return ResponseEntity.ok().body(new DetalhesPaisDTO(pais));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DetalhesPaisDTO> buscarPorId(@PathVariable Long id){
        var pais = repository.getReferenceById(id);
        return ResponseEntity.ok().body(new DetalhesPaisDTO(pais));
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        var pais = repository.getReferenceById(id);
        repository.deleteById(pais.getCodigo());
        return ResponseEntity.noContent().build();
    }

}
