package br.com.fiap.challenge.sprint1.model.figuraPublica.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizarFiguraDTO(
        @NotNull
        Long codigo,String nome, String nomeArtistico, String nomeRedeSocial) {
}
