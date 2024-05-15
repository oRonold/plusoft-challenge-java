package br.com.fiap.challenge.sprint1.controller;

import br.com.fiap.challenge.sprint1.model.cliente.dto.AtualizarClienteDTO;
import br.com.fiap.challenge.sprint1.model.cliente.dto.CadastrarClienteDTO;
import br.com.fiap.challenge.sprint1.model.cliente.dto.DetalhesClienteDTO;
import br.com.fiap.challenge.sprint1.model.endereco.EnderecoCliente;
import br.com.fiap.challenge.sprint1.model.endereco.dto.AtualizarEnderecoClienteDTO;
import br.com.fiap.challenge.sprint1.model.endereco.dto.CriarEnderecoClienteDTO;
import br.com.fiap.challenge.sprint1.model.endereco.dto.DetalhesEnderecoClienteDTO;
import br.com.fiap.challenge.sprint1.repository.EnderecoClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/endereco")
public class EnderecoClienteController {

    @Autowired
    private EnderecoClienteRepository repository;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<DetalhesEnderecoClienteDTO> criar(@RequestBody @Valid CadastrarClienteDTO dto, UriComponentsBuilder builder){
        var endereco = new EnderecoCliente(dto);
        repository.save(endereco);
        var uri = builder.path("/{id}").buildAndExpand(endereco.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesEnderecoClienteDTO(endereco));
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DetalhesEnderecoClienteDTO>> listar(Pageable pageable){
        var page = repository.findAll(pageable).map(DetalhesEnderecoClienteDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity<DetalhesEnderecoClienteDTO> atualizar(@RequestBody @Valid AtualizarEnderecoClienteDTO dto){
        var endereco = repository.getReferenceById(dto.codigo());
        endereco.atualizar(dto);
        return ResponseEntity.ok().body(new DetalhesEnderecoClienteDTO(endereco));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DetalhesEnderecoClienteDTO> buscarPorId(@PathVariable Long id){
        var endereco = repository.getReferenceById(id);
        return ResponseEntity.ok().body(new DetalhesEnderecoClienteDTO(endereco));
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        var endereco = repository.getReferenceById(id);
        repository.deleteById(endereco.getCodigo());
        return ResponseEntity.noContent().build();
    }

}
