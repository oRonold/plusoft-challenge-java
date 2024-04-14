package br.com.fiap.challenge.sprint1.model.endereco.pais.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AtualizarPaisDTO(@NotNull Long codigo, String nome, String codPais) {
}
