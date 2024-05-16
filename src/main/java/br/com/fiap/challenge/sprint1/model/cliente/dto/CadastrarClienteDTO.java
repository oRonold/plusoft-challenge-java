package br.com.fiap.challenge.sprint1.model.cliente.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record CadastrarClienteDTO(
        @NotEmpty
        String nome,
        @NotNull
        @Past
        LocalDate dataNascimento,
        @NotEmpty
        @Length(max = 11)
        String cpf,
        @NotEmpty
        @Length(max = 15)
        String telefone,
        @NotEmpty
        @Email
        String email,
        @NotEmpty
        String senha,
        @NotEmpty
        String nomeRamo,
        @NotEmpty
        String descRamo,
        @NotEmpty
        String numeroLogradouro,

        String pontoReferencia,
        @NotEmpty
        String nomeLogradouro,
        @NotEmpty
        @Length(max = 9)
        String cep,
        @NotEmpty
        String nomeBairro,
        String zonaBairro,
        @NotEmpty
        String nomeCidade,
        @NotEmpty
        String numeroDDD,
        @NotEmpty
        String nomeEstado,
        @NotEmpty
        String nomePais,
        @NotEmpty
        String codPais
) {
}
