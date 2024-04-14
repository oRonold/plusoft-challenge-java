package br.com.fiap.challenge.sprint1.model.ramo.dto;

import jakarta.validation.constraints.NotEmpty;

public record CriarRamoDTO(
        @NotEmpty
        String nome,
        @NotEmpty
        String descricao) {
}
