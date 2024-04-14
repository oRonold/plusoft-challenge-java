package br.com.fiap.challenge.sprint1.model.pesquisa.dto;

import br.com.fiap.challenge.sprint1.model.pesquisa.Pesquisa;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record DetalhesPesquisaDTO(Long codigo, String descricao,
                                  @JsonFormat(pattern = "dd/MM/yyyy")
                                  LocalDate dataPesquisa) {

    public DetalhesPesquisaDTO(Pesquisa pesquisa){
        this(pesquisa.getCodigo(), pesquisa.getDescricao(), pesquisa.getDataPesquisa());
    }

}
