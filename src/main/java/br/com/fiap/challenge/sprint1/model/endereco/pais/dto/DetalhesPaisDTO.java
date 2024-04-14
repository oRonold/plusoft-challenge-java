package br.com.fiap.challenge.sprint1.model.endereco.pais.dto;

import br.com.fiap.challenge.sprint1.model.endereco.pais.Pais;

public record DetalhesPaisDTO(Long codigo, String pais, String codPais) {

    public DetalhesPaisDTO(Pais pais){
        this(pais.getCodigo(), pais.getNome(), pais.getNumeroCodigo());
    }

}
