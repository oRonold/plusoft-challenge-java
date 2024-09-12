package br.com.fiap.challenge.sprint1.controller;

import br.com.fiap.challenge.sprint1.model.dto.LoginDTO;
import br.com.fiap.challenge.sprint1.model.dto.TokenJwtDTO;
import br.com.fiap.challenge.sprint1.model.usuario.dto.DetalhesUsuarioDTO;
import br.com.fiap.challenge.sprint1.model.usuario.dto.ListagemUsuarioDTO;
import br.com.fiap.challenge.sprint1.model.usuario.dto.PublicListaUsuarioDTO;
import br.com.fiap.challenge.sprint1.service.UsuarioService;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuario", description = "Operações relacionadas ao Usuário")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/publico/login")
    @Operation(summary = "Login do Usuário", description = "Autentica o Usuário e retorna o token JWT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login com sucesso", content = @Content(schema = @Schema(implementation = TokenJwtDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    public ResponseEntity<TokenJwtDTO> login(@RequestBody @Valid LoginDTO dto){
        var token = service.login(dto);
        return ResponseEntity.ok(new TokenJwtDTO(token));
    }

    @GetMapping("/publico/listar")
    @Operation(summary = "Listagem Usuários Público", description = "Lista todos os Usuários da aplicação sem necessitar autenticação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário retornados", content = @Content(schema = @Schema(implementation = PublicListaUsuarioDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "204", description = "Nenhum Usuário cadastrado", content = @Content)
    })
    public ResponseEntity<Page<PublicListaUsuarioDTO>> listar(Pageable pageable){
        var page = service.listar(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/detalhes")
    @Operation(summary = "Detalhes Usuário", description = "Retorna os detalhes do Usuário junto com a lista de Pesquisas conforme o token")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário retornado", content = @Content(schema = @Schema(implementation = DetalhesUsuarioDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content)
    })
    public ResponseEntity<DetalhesUsuarioDTO> detalhes(){
        var usuario = service.detalhes();
        return ResponseEntity.ok(new DetalhesUsuarioDTO(usuario));
    }

}
