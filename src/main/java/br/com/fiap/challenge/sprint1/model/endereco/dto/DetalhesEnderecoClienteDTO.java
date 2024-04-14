package br.com.fiap.challenge.sprint1.model.endereco.dto;

import br.com.fiap.challenge.sprint1.model.endereco.EnderecoCliente;

public record DetalhesEnderecoClienteDTO(Long codigo, Long numero, String pontoReferencia) {

    public DetalhesEnderecoClienteDTO(EnderecoCliente enderecoCliente){
        this(enderecoCliente.getCodigo(), enderecoCliente.getNumero(), enderecoCliente.getPontoReferencia());
    }

}
