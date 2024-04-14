package br.com.fiap.challenge.sprint1.model.endereco.pais.dto;

import jakarta.validation.constraints.NotEmpty;

public record CriarPaisDTO(
        @NotEmpty
        String nome,
        @NotEmpty
        String numeroCodPais) {
}
