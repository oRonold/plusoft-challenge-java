package br.com.fiap.challenge.sprint1.model.endereco.cidade.dto;

import jakarta.validation.constraints.NotEmpty;

public record CriarCidadeDTO(
        @NotEmpty
        String nome,
        @NotEmpty
        String ddd) {
}
