package br.com.fiap.challenge.sprint1.model.pesquisa.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CriarPesquisaDTO(
        @NotEmpty
        String descricao,
        @NotNull
        LocalDate dataPesquisa) {
}
