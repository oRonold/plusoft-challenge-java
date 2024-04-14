package br.com.fiap.challenge.sprint1.model.endereco.bairro.dto;

import jakarta.validation.constraints.NotEmpty;

public record CriarBairroDTO(
        @NotEmpty
        String nome,
        String zona) {
}
