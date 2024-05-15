package br.com.fiap.challenge.sprint1.model.endereco.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizarEnderecoClienteDTO(@NotNull Long codigo, String numero, String pontoReferencia) {
}
