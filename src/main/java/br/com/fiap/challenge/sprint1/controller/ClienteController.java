package br.com.fiap.challenge.sprint1.controller;

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
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<DetalhesClienteDTO> cadastrar(@RequestBody @Valid CadastrarClienteDTO dto, UriComponentsBuilder builder){
        var cliente = service.cadastrar(dto);
        var uri = builder.path("/{id}").buildAndExpand(cliente.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesClienteDTO(cliente));
    }

    @GetMapping("/detalhes")
    public ResponseEntity<DetalhesClienteDTO> detalhes(){
        var cliente = service.detalhes();
        return ResponseEntity.ok().body(new DetalhesClienteDTO(cliente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhesClienteDTO> atualizar(@RequestBody @Valid AtualizarClienteDTO dto){
        var cliente = service.atualizar(dto);
        return ResponseEntity.ok().body(new DetalhesClienteDTO(cliente));
    }

}
