package br.com.fiap.challenge.sprint1.model.endereco.estado.dto;

import br.com.fiap.challenge.sprint1.model.endereco.estado.Estado;

public record DetalhesEstadoDTO(Long codigo, String nome) {

    public DetalhesEstadoDTO(Estado estado){
        this(estado.getCodigo(), estado.getNome());
    }

}
