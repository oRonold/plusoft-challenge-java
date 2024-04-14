package br.com.fiap.challenge.sprint1.model.ramo.dto;

import br.com.fiap.challenge.sprint1.model.ramo.Ramo;

public record DetalhesRamoDTO(Long codigo, String nome, String descricao) {

    public DetalhesRamoDTO(Ramo ramo){
        this(ramo.getCodigo(), ramo.getNome(), ramo.getDescricao());
    }

}
