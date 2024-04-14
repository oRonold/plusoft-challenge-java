package br.com.fiap.challenge.sprint1.model.endereco.logradouro.dto;

import jakarta.validation.constraints.NotEmpty;

public record CriarLogradouroDTO(
        @NotEmpty
        String nome,
        @NotEmpty
        String cep) {
}
