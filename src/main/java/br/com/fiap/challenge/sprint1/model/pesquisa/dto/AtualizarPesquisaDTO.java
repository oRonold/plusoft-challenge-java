package br.com.fiap.challenge.sprint1.model.pesquisa.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizarPesquisaDTO(
        @NotNull
        Long codigo, String descricao) {
}
