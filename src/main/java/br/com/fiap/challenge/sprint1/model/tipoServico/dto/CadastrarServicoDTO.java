package br.com.fiap.challenge.sprint1.model.tipoServico.dto;

import jakarta.validation.constraints.NotEmpty;

public record CadastrarServicoDTO(@NotEmpty String descricao) {
}
