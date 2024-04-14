package br.com.fiap.challenge.sprint1.model.cliente.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record CriarClienteDTO(
        @NotEmpty
        String nome,
        @NotNull
        @Past
        LocalDate dataNascimento,
        @NotEmpty
        String cpf,
        @NotEmpty
        String telefone) {
}
