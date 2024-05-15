package br.com.fiap.challenge.sprint1.model.cliente.dto;

import org.hibernate.validator.constraints.Length;

public record AtualizarClienteDTO(String nome,
                                  @Length(max = 15)
                                  String telefone, String senha, String nomeRamo, String descRamo) {
}
