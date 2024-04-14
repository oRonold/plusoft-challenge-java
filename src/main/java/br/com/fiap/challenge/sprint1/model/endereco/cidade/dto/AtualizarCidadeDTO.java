package br.com.fiap.challenge.sprint1.model.endereco.cidade.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizarCidadeDTO(@NotNull Long codigo, String nome) {
}
