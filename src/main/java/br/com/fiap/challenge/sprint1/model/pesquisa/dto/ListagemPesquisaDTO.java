package br.com.fiap.challenge.sprint1.model.pesquisa.dto;

import br.com.fiap.challenge.sprint1.model.figuraPublica.FiguraPublica;
import br.com.fiap.challenge.sprint1.model.pesquisa.Pesquisa;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ListagemPesquisaDTO(Long codigo, String descricao,
                                  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
                                  LocalDateTime dataPesquisa, String tipoServico) {

    public ListagemPesquisaDTO(Pesquisa pesquisa){
        this(pesquisa.getCodigo(), pesquisa.getDescricao(), pesquisa.getDataPesquisa(), pesquisa.getTipoServico().getDescricao());
    }

}
