package br.com.fiap.challenge.sprint1.model.endereco.bairro.dto;

import br.com.fiap.challenge.sprint1.model.endereco.bairro.Bairro;

public record DetalhesBairroDTO(Long codigo, String nome, String zona) {

    public DetalhesBairroDTO(Bairro bairro){
        this(bairro.getCodigo(), bairro.getNome(), bairro.getZona());
    }

}
