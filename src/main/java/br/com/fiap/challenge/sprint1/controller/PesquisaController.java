package br.com.fiap.challenge.sprint1.controller;

import br.com.fiap.challenge.sprint1.model.pesquisa.dto.AdicionarFigPublicaDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.CriarPesquisaDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.DetalhesPesquisaDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.ListagemPesquisaDTO;
import br.com.fiap.challenge.sprint1.repository.PesquisaRepository;
import br.com.fiap.challenge.sprint1.service.PesquisaService;
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

import java.util.List;

@RestController
@RequestMapping("/pesquisas")
@Tag(name = "Pesquisa", description = "Operações relacionadas à Pesquisa")
public class PesquisaController {

    @Autowired
    private PesquisaService service;

    @PostMapping
    @Transactional
    @Operation(summary = "Cria uma nova pesquisa para o Usuário", description = "Retorna as informações da Pesquisa criada relacionadas ao token do Usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastro com sucesso", content = @Content(schema = @Schema(implementation = DetalhesPesquisaDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<DetalhesPesquisaDTO> criar(@RequestBody @Valid CriarPesquisaDTO dto, UriComponentsBuilder builder){
        var pesquisa = service.criarPesquisa(dto);
        var uri = builder.path("/{id}").buildAndExpand(pesquisa.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesPesquisaDTO(pesquisa));
    }

    @GetMapping
    @Operation(summary = "Pesquisas do Usuario", description = "retorna todas as pesquisas daquele Usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pesquisas retornadas", content = @Content(schema = @Schema(implementation = ListagemPesquisaDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content)
    })
    public ResponseEntity<List<ListagemPesquisaDTO>> listar(){
        var page = service.listarPesquisaUsuario();
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca Pesquisa por Id", description = "Retorna à pesquisa pelo Id se ele existir ou pertencer ao Usuário passado pelo token")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pesquisa encontrada", content = @Content(schema = @Schema(implementation = DetalhesPesquisaDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pesquisa não encontrada", content = @Content)
    })
    public ResponseEntity<DetalhesPesquisaDTO> buscarPorId(@PathVariable Long id){
        var pesquisa = service.buscarPorId(id);
        return ResponseEntity.ok().body(new DetalhesPesquisaDTO(pesquisa));
    }

    @GetMapping("/em-andamento")
    @Operation(summary = "Pesquisas em Andamento", description = "Retorna todas as Pesquisas em Andamento daquele Usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pesquisas retornadas", content = @Content(schema = @Schema(implementation = DetalhesPesquisaDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content)
    })
    public ResponseEntity<List<DetalhesPesquisaDTO>> buscarPesquisaEmAndamento(){
        var pesquisa = service.pesquisaEmAndamento();
        return ResponseEntity.ok(pesquisa);
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Adiciona Figura Pública", description = "Adiciona uma nova figura pública aquela Pesquisa se ela existir ou pertencer ao Usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Adicionado com sucesso", content = @Content(schema = @Schema(implementation = DetalhesPesquisaDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pesquisa não encontrada", content = @Content)
    })
    public ResponseEntity<DetalhesPesquisaDTO> adicionarFiguraPublica(@PathVariable Long id, @RequestBody @Valid AdicionarFigPublicaDTO dto){
        var pesquisa = service.addFiguraPublica(id, dto);
        return ResponseEntity.ok().body(new DetalhesPesquisaDTO(pesquisa));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Define como Concluída", description = "Retorna a Pesquisa com Status 'CONCLUIDA' se ela existir ou pertencer ao Usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Excluido com sucesso", content = @Content),
            @ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pesquisa não encontrada", content = @Content)
    })
    public ResponseEntity<ListagemPesquisaDTO> excluir(@PathVariable Long id){
        var pesquisa = service.excluir(id);
        return ResponseEntity.ok().body(new ListagemPesquisaDTO(pesquisa));
    }

}
