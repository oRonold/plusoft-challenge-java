package br.com.fiap.challenge.sprint1.model.figuraPublica.dto;

import jakarta.validation.constraints.NotEmpty;

public record CriarFiguraDTO(
        @NotEmpty
        String nome,
        String nomeArtistico,
        @NotEmpty
        String nomeRedeSocial) {
}
