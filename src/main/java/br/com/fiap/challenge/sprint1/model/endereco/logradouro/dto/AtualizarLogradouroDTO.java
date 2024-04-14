package br.com.fiap.challenge.sprint1.model.endereco.logradouro.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizarLogradouroDTO(@NotNull Long codigo, String nome, String cep) {
}
