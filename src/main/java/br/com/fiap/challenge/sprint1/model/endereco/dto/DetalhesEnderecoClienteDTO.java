package br.com.fiap.challenge.sprint1.model.endereco.dto;

import br.com.fiap.challenge.sprint1.model.endereco.EnderecoCliente;
import br.com.fiap.challenge.sprint1.model.endereco.bairro.dto.DetalhesBairroDTO;
import br.com.fiap.challenge.sprint1.model.endereco.cidade.dto.DetalhesCidadeDTO;
import br.com.fiap.challenge.sprint1.model.endereco.estado.dto.DetalhesEstadoDTO;
import br.com.fiap.challenge.sprint1.model.endereco.logradouro.dto.DetalhesLogradouroDTO;
import br.com.fiap.challenge.sprint1.model.endereco.pais.dto.DetalhesPaisDTO;

public record DetalhesEnderecoClienteDTO(Long codigo, String numero, String pontoReferencia, DetalhesLogradouroDTO logradouro,
                                         DetalhesBairroDTO bairro, DetalhesCidadeDTO cidade, DetalhesEstadoDTO estado, DetalhesPaisDTO pais) {

    public DetalhesEnderecoClienteDTO(EnderecoCliente enderecoCliente){
        this(enderecoCliente.getCodigo(), enderecoCliente.getNumero(), enderecoCliente.getPontoReferencia(),
                new DetalhesLogradouroDTO(enderecoCliente.getLogradouro()),
                new DetalhesBairroDTO(enderecoCliente.getLogradouro().getBairro()),
                new DetalhesCidadeDTO(enderecoCliente.getLogradouro().getBairro().getCidade()),
                new DetalhesEstadoDTO(enderecoCliente.getLogradouro().getBairro().getCidade().getEstado()),
                new DetalhesPaisDTO(enderecoCliente.getLogradouro().getBairro().getCidade().getEstado().getPais()));
    }

}
