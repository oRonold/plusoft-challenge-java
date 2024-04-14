package br.com.fiap.challenge.sprint1.model.ramo.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizarRamoDTO(@NotNull Long codigo, String nome, String descricao) {
}
