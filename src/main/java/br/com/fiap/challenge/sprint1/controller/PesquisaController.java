package br.com.fiap.challenge.sprint1.controller;

import br.com.fiap.challenge.sprint1.model.pesquisa.Pesquisa;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.AtualizarPesquisaDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.CriarPesquisaDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.DetalhesPesquisaDTO;
import br.com.fiap.challenge.sprint1.repository.PesquisaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pesquisa")
public class PesquisaController {

    @Autowired
    private PesquisaRepository repository;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<DetalhesPesquisaDTO> criar(@RequestBody @Valid CriarPesquisaDTO dto, UriComponentsBuilder builder){
        var pesquisa = new Pesquisa(dto);
        repository.save(pesquisa);
        var uri = builder.path("/{id}").buildAndExpand(pesquisa.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesPesquisaDTO(pesquisa));
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DetalhesPesquisaDTO>> listar(Pageable pageable){
        var page = repository.findAll(pageable).map(DetalhesPesquisaDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity<DetalhesPesquisaDTO> atualizar(@RequestBody @Valid AtualizarPesquisaDTO dto){
        var pesquisa = repository.getReferenceById(dto.codigo());
        pesquisa.atualizar(dto);
        return ResponseEntity.ok().body(new DetalhesPesquisaDTO(pesquisa));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DetalhesPesquisaDTO> buscarPorId(@PathVariable Long id){
        var pesquisa = repository.getReferenceById(id);
        return ResponseEntity.ok().body(new DetalhesPesquisaDTO(pesquisa));
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        var pesquisa = repository.getReferenceById(id);
        repository.deleteById(pesquisa.getCodigo());
        return ResponseEntity.noContent().build();
    }

}
