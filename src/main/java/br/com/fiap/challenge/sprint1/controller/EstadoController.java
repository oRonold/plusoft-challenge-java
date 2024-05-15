package br.com.fiap.challenge.sprint1.controller;

import br.com.fiap.challenge.sprint1.model.cliente.dto.CadastrarClienteDTO;
import br.com.fiap.challenge.sprint1.model.endereco.cidade.Cidade;
import br.com.fiap.challenge.sprint1.model.endereco.cidade.dto.AtualizarCidadeDTO;
import br.com.fiap.challenge.sprint1.model.endereco.cidade.dto.CriarCidadeDTO;
import br.com.fiap.challenge.sprint1.model.endereco.cidade.dto.DetalhesCidadeDTO;
import br.com.fiap.challenge.sprint1.model.endereco.estado.Estado;
import br.com.fiap.challenge.sprint1.model.endereco.estado.dto.CriarEstadoDTO;
import br.com.fiap.challenge.sprint1.model.endereco.estado.dto.DetalhesEstadoDTO;
import br.com.fiap.challenge.sprint1.repository.EstadoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/estado")
public class EstadoController {

    @Autowired
    private EstadoRepository repository;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<DetalhesEstadoDTO> criar(@RequestBody @Valid CadastrarClienteDTO dto, UriComponentsBuilder builder){
        var estado = new Estado(dto);
        repository.save(estado);
        var uri = builder.path("/{id}").buildAndExpand(estado.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesEstadoDTO(estado));
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DetalhesEstadoDTO>> listar(Pageable pageable){
        var page = repository.findAll(pageable).map(DetalhesEstadoDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity<DetalhesEstadoDTO> atualizar(@RequestBody @Valid AtualizarEstadoDTO dto){
        var estado = repository.getReferenceById(dto.codigo());
        estado.atualizar(dto);
        return ResponseEntity.ok().body(new DetalhesEstadoDTO(estado));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DetalhesEstadoDTO> buscarPorId(@PathVariable Long id){
        var estado = repository.getReferenceById(id);
        return ResponseEntity.ok().body(new DetalhesEstadoDTO(estado));
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        var estado = repository.getReferenceById(id);
        repository.deleteById(estado.getCodigo());
        return ResponseEntity.noContent().build();
    }

}
