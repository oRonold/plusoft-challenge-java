package br.com.fiap.challenge.sprint1.controller;

import br.com.fiap.challenge.sprint1.model.cliente.dto.CadastrarClienteDTO;
import br.com.fiap.challenge.sprint1.model.endereco.cidade.Cidade;
import br.com.fiap.challenge.sprint1.model.endereco.cidade.dto.AtualizarCidadeDTO;
import br.com.fiap.challenge.sprint1.model.endereco.cidade.dto.CriarCidadeDTO;
import br.com.fiap.challenge.sprint1.model.endereco.cidade.dto.DetalhesCidadeDTO;
import br.com.fiap.challenge.sprint1.repository.CidadeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

    @Autowired
    private CidadeRepository repository;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<DetalhesCidadeDTO> criar(@RequestBody @Valid CadastrarClienteDTO dto, UriComponentsBuilder builder){
        var cidade = new Cidade(dto);
        repository.save(cidade);
        var uri = builder.path("/{id}").buildAndExpand(cidade.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesCidadeDTO(cidade));
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DetalhesCidadeDTO>> listar(Pageable pageable){
        var page = repository.findAll(pageable).map(DetalhesCidadeDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity<DetalhesCidadeDTO> atualizar(@RequestBody @Valid AtualizarCidadeDTO dto){
        var cidade = repository.getReferenceById(dto.codigo());
        cidade.atualizar(dto);
        return ResponseEntity.ok().body(new DetalhesCidadeDTO(cidade));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DetalhesCidadeDTO> buscarPorId(@PathVariable Long id){
        var cidade = repository.getReferenceById(id);
        return ResponseEntity.ok().body(new DetalhesCidadeDTO(cidade));
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        var cidade = repository.getReferenceById(id);
        repository.deleteById(cidade.getCodigo());
        return ResponseEntity.noContent().build();
    }

}
