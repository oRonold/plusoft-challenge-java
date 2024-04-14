package br.com.fiap.challenge.sprint1.model.tipoServico.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizarServicoDTO(@NotNull Long codigo, String descricao) {
}
