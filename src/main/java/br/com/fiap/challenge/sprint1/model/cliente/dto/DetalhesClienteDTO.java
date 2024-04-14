package br.com.fiap.challenge.sprint1.model.cliente.dto;

import br.com.fiap.challenge.sprint1.model.cliente.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record DetalhesClienteDTO(Long codigo, String nome,
                                 @JsonFormat(pattern = "dd/MM/yyyy")
                                 LocalDate dataNascimento, String cpf, String telefone) {

    public DetalhesClienteDTO(Cliente cliente){
        this(cliente.getCodigo(), cliente.getNome(), cliente.getDataNascimento(), cliente.getCpf(), cliente.getTelefone());
    }

}
