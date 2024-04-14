package br.com.fiap.challenge.sprint1.model.tipoServico.dto;

import br.com.fiap.challenge.sprint1.model.tipoServico.TipoServico;

public record DetalhesServicoDTO(Long codigo, String descricao) {

    public DetalhesServicoDTO(TipoServico servico){
        this(servico.getCodigo(), servico.getDescricao());
    }

}
