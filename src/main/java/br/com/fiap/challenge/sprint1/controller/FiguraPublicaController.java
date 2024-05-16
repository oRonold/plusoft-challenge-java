package br.com.fiap.challenge.sprint1.controller;

import br.com.fiap.challenge.sprint1.model.figuraPublica.FiguraPublica;
import br.com.fiap.challenge.sprint1.model.figuraPublica.dto.AtualizarFiguraDTO;
import br.com.fiap.challenge.sprint1.model.figuraPublica.dto.DetalhesFiguraDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.CriarPesquisaDTO;
import br.com.fiap.challenge.sprint1.repository.FiguraPublicaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/figura")
public class FiguraPublicaController {

    @Autowired
    private FiguraPublicaRepository repository;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<DetalhesFiguraDTO> cadastrar(@RequestBody @Valid CriarPesquisaDTO dto, UriComponentsBuilder builder){
        var figura = new FiguraPublica(dto);
        repository.save(figura);
        var uri = builder.path("/{id}").buildAndExpand(figura.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesFiguraDTO(figura));
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DetalhesFiguraDTO>> listar(Pageable pageable){
        var page = repository.findAll(pageable).map(DetalhesFiguraDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity<DetalhesFiguraDTO> atualizar(@RequestBody @Valid AtualizarFiguraDTO dto){
        var figura = repository.getReferenceById(dto.codigo());
        figura.atualizar(dto);
        return ResponseEntity.ok().body(new DetalhesFiguraDTO(figura));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DetalhesFiguraDTO> buscarPorId(@PathVariable Long id){
        var figura = repository.getReferenceById(id);
        return ResponseEntity.ok().body(new DetalhesFiguraDTO(figura));
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        var figura = repository.getReferenceById(id);
        repository.deleteById(figura.getCodigo());
        return ResponseEntity.noContent().build();
    }

}
