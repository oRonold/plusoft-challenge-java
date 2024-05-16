package br.com.fiap.challenge.sprint1.controller;

import br.com.fiap.challenge.sprint1.model.pesquisa.dto.AdicionarFigPublicaDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.CriarPesquisaDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.DetalhesPesquisaDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.ListagemPesquisaDTO;
import br.com.fiap.challenge.sprint1.repository.PesquisaRepository;
import br.com.fiap.challenge.sprint1.service.PesquisaService;
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

    @Autowired
    private PesquisaService service;

    @PostMapping("/usuario/{idUsuario}")
    @Transactional
    public ResponseEntity<DetalhesPesquisaDTO> criar(@PathVariable Long idUsuario, @RequestBody @Valid CriarPesquisaDTO dto, UriComponentsBuilder builder){
        var pesquisa = service.criarPesquisa(idUsuario, dto);
        var uri = builder.path("/{id}").buildAndExpand(pesquisa.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesPesquisaDTO(pesquisa));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemPesquisaDTO>> listar(Pageable pageable){
        var page = repository.findAll(pageable).map(ListagemPesquisaDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesPesquisaDTO> buscarPorId(@PathVariable Long id){
        var pesquisa = repository.getReferenceById(id);
        return ResponseEntity.ok().body(new DetalhesPesquisaDTO(pesquisa));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesPesquisaDTO> adicionarFiguraPublica(@PathVariable Long id, @RequestBody @Valid AdicionarFigPublicaDTO dto){
        var pesquisa = service.addFiguraPublica(id, dto);
        return ResponseEntity.ok().body(new DetalhesPesquisaDTO(pesquisa));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        var pesquisa = repository.getReferenceById(id);
        repository.delete(pesquisa);
        return ResponseEntity.noContent().build();
    }

}
