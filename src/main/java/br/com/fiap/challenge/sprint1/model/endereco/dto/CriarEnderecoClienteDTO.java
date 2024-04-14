package br.com.fiap.challenge.sprint1.model.endereco.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CriarEnderecoClienteDTO(
        @NotNull
        Long numero,
        @NotEmpty
        String pontoReferencia) {
}
