package br.com.fiap.challenge.sprint1.model.cliente.dto;

import br.com.fiap.challenge.sprint1.model.cliente.Cliente;
import br.com.fiap.challenge.sprint1.model.ramo.dto.DetalhesRamoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record ListagemClientesDTO(Long codigo, String nome,
                                  @JsonFormat(pattern = "dd/MM/yyyy")
                                  LocalDate dataNascimento, String cpf, String telefone,
                                  DetalhesRamoDTO ramo) {

    public ListagemClientesDTO(Cliente cliente){
        this(cliente.getCodigo(), cliente.getNome(), cliente.getDataNascimento(), cliente.getCpf(), cliente.getTelefone(),
                new DetalhesRamoDTO(cliente.getRamo()));
    }

}
