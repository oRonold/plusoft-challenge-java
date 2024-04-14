package br.com.fiap.challenge.sprint1.model.endereco.cidade.dto;

import br.com.fiap.challenge.sprint1.model.endereco.cidade.Cidade;

public record DetalhesCidadeDTO(Long codigo, String nome, String ddd) {

    public DetalhesCidadeDTO(Cidade cidade){
        this(cidade.getCodigo(), cidade.getNome(), cidade.getDdd());
    }

}
