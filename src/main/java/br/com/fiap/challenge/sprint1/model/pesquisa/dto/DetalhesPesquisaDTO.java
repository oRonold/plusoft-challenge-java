package br.com.fiap.challenge.sprint1.model.pesquisa.dto;

import br.com.fiap.challenge.sprint1.model.figuraPublica.dto.DetalhesFiguraDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.Pesquisa;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

public record DetalhesPesquisaDTO(Long codigo, String descricao,
                                  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
                                  LocalDateTime dataPesquisa, String tipoServico, ArrayList<DetalhesFiguraDTO> figuraPublica) {

    public DetalhesPesquisaDTO(Pesquisa pesquisa){
        this(pesquisa.getCodigo(), pesquisa.getDescricao(), pesquisa.getDataPesquisa(), pesquisa.getTipoServico().getDescricao(),
                new ArrayList<>(pesquisa.getFiguraPublica().stream().map(fig -> new DetalhesFiguraDTO(fig.getCodigo(), fig.getNome(), fig.getNomeArtistico(), fig.getNomeRedeSocial(), fig.getScore().getNumeroScore())).toList()));
    }

}
