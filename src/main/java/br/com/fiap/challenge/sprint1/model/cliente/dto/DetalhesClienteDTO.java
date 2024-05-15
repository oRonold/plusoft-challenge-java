package br.com.fiap.challenge.sprint1.model.cliente.dto;

import br.com.fiap.challenge.sprint1.model.cliente.Cliente;
import br.com.fiap.challenge.sprint1.model.endereco.bairro.dto.DetalhesBairroDTO;
import br.com.fiap.challenge.sprint1.model.endereco.cidade.dto.DetalhesCidadeDTO;
import br.com.fiap.challenge.sprint1.model.endereco.dto.DetalhesEnderecoClienteDTO;
import br.com.fiap.challenge.sprint1.model.endereco.estado.dto.DetalhesEstadoDTO;
import br.com.fiap.challenge.sprint1.model.endereco.logradouro.dto.DetalhesLogradouroDTO;
import br.com.fiap.challenge.sprint1.model.endereco.pais.dto.DetalhesPaisDTO;
import br.com.fiap.challenge.sprint1.model.ramo.dto.DetalhesRamoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record DetalhesClienteDTO(Long codigo, String nome,
                                 @JsonFormat(pattern = "dd/MM/yyyy")
                                 LocalDate dataNascimento, String cpf, String telefone, DetalhesRamoDTO ramo,
                                 ArrayList<DetalhesEnderecoClienteDTO> enderecoCliente) {

    public DetalhesClienteDTO(Cliente cliente){
        this(cliente.getCodigo(), cliente.getNome(), cliente.getDataNascimento(), cliente.getCpf(), cliente.getTelefone(),
                new DetalhesRamoDTO(cliente.getRamo()), new ArrayList<>(cliente.getEnderecoClientes().stream().map(endereco -> new DetalhesEnderecoClienteDTO(endereco.getCodigo(), endereco.getNumero(), endereco.getPontoReferencia(),
                        new DetalhesLogradouroDTO(endereco.getLogradouro()),
                        new DetalhesBairroDTO(endereco.getLogradouro().getBairro()),
                        new DetalhesCidadeDTO(endereco.getLogradouro().getBairro().getCidade()),
                        new DetalhesEstadoDTO(endereco.getLogradouro().getBairro().getCidade().getEstado()),
                        new DetalhesPaisDTO(endereco.getLogradouro().getBairro().getCidade().getEstado().getPais()))).toList()));
    }

}
