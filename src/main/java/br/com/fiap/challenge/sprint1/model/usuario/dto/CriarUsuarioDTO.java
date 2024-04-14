package br.com.fiap.challenge.sprint1.model.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record CriarUsuarioDTO(
        @NotEmpty
        String nome,
        @NotEmpty
        @Email
        String email,
        @NotEmpty
        String senha) {
}
