package br.com.fiap.challenge.sprint1.model.usuario.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizarUsuarioDTO(@NotNull Long codigo, String nome, String email, String senha) {
}
