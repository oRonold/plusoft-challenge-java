package br.com.fiap.challenge.sprint1.model.figuraPublica.dto;

import br.com.fiap.challenge.sprint1.model.figuraPublica.FiguraPublica;

public record DetalhesFiguraDTO(Long codigo, String nome, String nomeArtistico, String nomeRedeSocial) {

    public DetalhesFiguraDTO(FiguraPublica figura){
        this(figura.getCodigo(), figura.getNome(), figura.getNomeArtistico(), figura.getNomeRedeSocial());
    }

}

