package br.com.fiap.challenge.sprint1.controller;

import br.com.fiap.challenge.sprint1.model.cliente.dto.CadastrarClienteDTO;
import br.com.fiap.challenge.sprint1.model.endereco.bairro.Bairro;
import br.com.fiap.challenge.sprint1.model.endereco.bairro.dto.AtualizarBairroDTO;
import br.com.fiap.challenge.sprint1.model.endereco.bairro.dto.CriarBairroDTO;
import br.com.fiap.challenge.sprint1.model.endereco.bairro.dto.DetalhesBairroDTO;
import br.com.fiap.challenge.sprint1.repository.BairroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/bairro")
public class BairroController {

    @Autowired
    private BairroRepository repository;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<DetalhesBairroDTO> criar(@RequestBody @Valid CadastrarClienteDTO dto, UriComponentsBuilder builder){
        var bairro = new Bairro(dto);
        repository.save(bairro);
        var uri = builder.path("/{id}").buildAndExpand(bairro.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesBairroDTO(bairro));
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DetalhesBairroDTO>> listar(Pageable pageable){
        var page = repository.findAll(pageable).map(DetalhesBairroDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity<DetalhesBairroDTO> atualizar(@RequestBody @Valid AtualizarBairroDTO dto){
        var bairro = repository.getReferenceById(dto.codigo());
        bairro.atualizar(dto);
        return ResponseEntity.ok().body(new DetalhesBairroDTO(bairro));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DetalhesBairroDTO> buscarPorId(@PathVariable Long id){
        var bairro = repository.getReferenceById(id);
        return ResponseEntity.ok().body(new DetalhesBairroDTO(bairro));
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        var bairro = repository.getReferenceById(id);
        repository.deleteById(bairro.getCodigo());
        return ResponseEntity.noContent().build();
    }

}
