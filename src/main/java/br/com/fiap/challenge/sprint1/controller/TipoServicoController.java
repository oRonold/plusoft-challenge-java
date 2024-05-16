package br.com.fiap.challenge.sprint1.controller;

import br.com.fiap.challenge.sprint1.model.pesquisa.dto.CriarPesquisaDTO;
import br.com.fiap.challenge.sprint1.model.tipoServico.TipoServico;
import br.com.fiap.challenge.sprint1.model.tipoServico.dto.AtualizarServicoDTO;
import br.com.fiap.challenge.sprint1.model.tipoServico.dto.DetalhesServicoDTO;
import br.com.fiap.challenge.sprint1.repository.TipoServicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/servico")
public class TipoServicoController {

    @Autowired
    private TipoServicoRepository repository;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<DetalhesServicoDTO> cadastrar(@RequestBody @Valid CriarPesquisaDTO dto, UriComponentsBuilder builder){
        var tipoServico = new TipoServico(dto);
        repository.save(tipoServico);
        var uri = builder.path("/{id}").buildAndExpand(tipoServico.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesServicoDTO(tipoServico));
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DetalhesServicoDTO>> listar(Pageable pageable){
        var page = repository.findAll(pageable).map(DetalhesServicoDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity<DetalhesServicoDTO> atualizar(@RequestBody @Valid AtualizarServicoDTO dto){
        var pesquisa = repository.getReferenceById(dto.codigo());
        pesquisa.atualizar(dto);
        return ResponseEntity.ok().body(new DetalhesServicoDTO(pesquisa));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DetalhesServicoDTO> buscarPorId(@PathVariable Long id){
        var servico = repository.getReferenceById(id);
        return ResponseEntity.ok().body(new DetalhesServicoDTO(servico));
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        var servico = repository.getReferenceById(id);
        repository.deleteById(servico.getCodigo());
        return ResponseEntity.noContent().build();
    }

}
