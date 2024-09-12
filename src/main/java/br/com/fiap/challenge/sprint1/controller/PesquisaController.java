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

import java.util.List;

@RestController
@RequestMapping("/pesquisas")
public class PesquisaController {

    @Autowired
    private PesquisaService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesPesquisaDTO> criar(@RequestBody @Valid CriarPesquisaDTO dto, UriComponentsBuilder builder){
        var pesquisa = service.criarPesquisa(dto);
        var uri = builder.path("/{id}").buildAndExpand(pesquisa.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesPesquisaDTO(pesquisa));
    }

    @GetMapping
    public ResponseEntity<List<ListagemPesquisaDTO>> listar(){
        var page = service.listarPesquisaUsuario();
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesPesquisaDTO> buscarPorId(@PathVariable Long id){
        var pesquisa = service.buscarPorId(id);
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
    public ResponseEntity<ListagemPesquisaDTO> excluir(@PathVariable Long id){
        var pesquisa = service.excluir(id);
        return ResponseEntity.ok().body(new ListagemPesquisaDTO(pesquisa));
    }

}
