package br.com.fiap.challenge.sprint1.model.pesquisa.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AdicionarFigPublicaDTO(
        @NotEmpty
        String nomeFigPublica,
        String nomeArtistico,
        @NotEmpty
        String usuarioRedeSocial,
        @NotEmpty
        String nomeCategoria,
        @NotNull
        BigDecimal numeroScore) {
}
