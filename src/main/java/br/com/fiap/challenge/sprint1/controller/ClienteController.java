package br.com.fiap.challenge.sprint1.controller;

import br.com.fiap.challenge.sprint1.model.cliente.dto.AtualizarClienteDTO;
import br.com.fiap.challenge.sprint1.model.cliente.dto.CadastrarClienteDTO;
import br.com.fiap.challenge.sprint1.model.cliente.dto.DetalhesClienteDTO;
import br.com.fiap.challenge.sprint1.model.cliente.dto.ListagemClientesDTO;
import br.com.fiap.challenge.sprint1.repository.ClienteRepository;
import br.com.fiap.challenge.sprint1.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Cliente", description = "Operações relacionadas para o Cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping("/publico/cadastrar")
    @Transactional
    @Operation(summary = "Cadastro de cliente", description = "Retorna os dados completos do novo cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastro com sucesso", content = @Content(schema = @Schema(implementation = DetalhesClienteDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<DetalhesClienteDTO> cadastrar(@RequestBody @Valid CadastrarClienteDTO dto, UriComponentsBuilder builder){
        var cliente = service.cadastrar(dto);
        var uri = builder.path("/{id}").buildAndExpand(cliente.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesClienteDTO(cliente));
    }

    @GetMapping("/detalhes")
    @Operation(summary = "Detalhes do cliente", description = "Retorna os detalhes do cliente conforme o token passado na autenticação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente retornado", content = @Content(schema = @Schema(implementation = DetalhesClienteDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content)
    })
    public ResponseEntity<DetalhesClienteDTO> detalhes(){
        var cliente = service.detalhes();
        return ResponseEntity.ok().body(new DetalhesClienteDTO(cliente));
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Atualiza as informações do cliente", description = "Retorna as informações atualizadas do cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Atualizado com sucesso", content = @Content(schema = @Schema(implementation = DetalhesClienteDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<DetalhesClienteDTO> atualizar(@RequestBody @Valid AtualizarClienteDTO dto){
        var cliente = service.atualizar(dto);
        return ResponseEntity.ok().body(new DetalhesClienteDTO(cliente));
    }

}
