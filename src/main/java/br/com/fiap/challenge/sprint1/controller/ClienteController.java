package br.com.fiap.challenge.sprint1.controller;

import br.com.fiap.challenge.sprint1.model.cliente.Cliente;
import br.com.fiap.challenge.sprint1.model.cliente.dto.AtualizarClienteDTO;
import br.com.fiap.challenge.sprint1.model.cliente.dto.CriarClienteDTO;
import br.com.fiap.challenge.sprint1.model.cliente.dto.DetalhesClienteDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.Pesquisa;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.AtualizarPesquisaDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.CriarPesquisaDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.DetalhesPesquisaDTO;
import br.com.fiap.challenge.sprint1.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<DetalhesClienteDTO> criar(@RequestBody @Valid CriarClienteDTO dto, UriComponentsBuilder builder){
        var cliente = new Cliente(dto);
        repository.save(cliente);
        var uri = builder.path("/{id}").buildAndExpand(cliente.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesClienteDTO(cliente));
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DetalhesClienteDTO>> listar(Pageable pageable){
        var page = repository.findAll(pageable).map(DetalhesClienteDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity<DetalhesClienteDTO> atualizar(@RequestBody @Valid AtualizarClienteDTO dto){
        var cliente = repository.getReferenceById(dto.codigo());
        cliente.atualizar(dto);
        return ResponseEntity.ok().body(new DetalhesClienteDTO(cliente));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DetalhesClienteDTO> buscarPorId(@PathVariable Long id){
        var cliente = repository.getReferenceById(id);
        return ResponseEntity.ok().body(new DetalhesClienteDTO(cliente));
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        var cliente = repository.getReferenceById(id);
        repository.deleteById(cliente.getCodigo());
        return ResponseEntity.noContent().build();
    }

}
