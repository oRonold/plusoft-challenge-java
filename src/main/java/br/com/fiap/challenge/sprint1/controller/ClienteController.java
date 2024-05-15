package br.com.fiap.challenge.sprint1.controller;

import br.com.fiap.challenge.sprint1.model.cliente.Cliente;
import br.com.fiap.challenge.sprint1.model.cliente.dto.AtualizarClienteDTO;
import br.com.fiap.challenge.sprint1.model.cliente.dto.CadastrarClienteDTO;
import br.com.fiap.challenge.sprint1.model.cliente.dto.DetalhesClienteDTO;
import br.com.fiap.challenge.sprint1.model.cliente.dto.ListagemClientesDTO;
import br.com.fiap.challenge.sprint1.repository.ClienteRepository;
import br.com.fiap.challenge.sprint1.service.ClienteService;
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

    @Autowired
    private ClienteService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesClienteDTO> cadastrar(@RequestBody @Valid CadastrarClienteDTO dto, UriComponentsBuilder builder){
        var cliente = service.cadastrarCliente(dto);
        var uri = builder.path("/{id}").buildAndExpand(cliente.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesClienteDTO(cliente));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemClientesDTO>> listar(Pageable pageable){
        var page = repository.findAll(pageable).map(ListagemClientesDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesClienteDTO> buscarPorId(@PathVariable Long id){
        var cliente = repository.getReferenceById(id);
        return ResponseEntity.ok().body(new DetalhesClienteDTO(cliente));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesClienteDTO> atualizar(@RequestBody @Valid AtualizarClienteDTO dto, @PathVariable Long id){
        var cliente = repository.getReferenceById(id);
        cliente.atualizar(dto);
        return ResponseEntity.ok().body(new DetalhesClienteDTO(cliente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
