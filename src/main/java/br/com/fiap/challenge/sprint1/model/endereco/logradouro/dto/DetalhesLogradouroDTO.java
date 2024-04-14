package br.com.fiap.challenge.sprint1.model.endereco.logradouro.dto;

import br.com.fiap.challenge.sprint1.model.endereco.logradouro.Logradouro;

public record DetalhesLogradouroDTO(Long codigo, String nome, String cep) {

    public DetalhesLogradouroDTO(Logradouro logradouro){
        this(logradouro.getCodigo(), logradouro.getNome(), logradouro.getCep());
    }

}
