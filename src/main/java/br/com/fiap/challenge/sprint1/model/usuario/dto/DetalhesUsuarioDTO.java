package br.com.fiap.challenge.sprint1.model.usuario.dto;

import br.com.fiap.challenge.sprint1.model.pesquisa.dto.ListagemPesquisaDTO;
import br.com.fiap.challenge.sprint1.model.usuario.Usuario;

import java.util.ArrayList;

public record DetalhesUsuarioDTO(Long codigo, String nome, String email, String senha, ArrayList<ListagemPesquisaDTO> pesquisas) {

    public DetalhesUsuarioDTO(Usuario usuario){
        this(usuario.getCodigo(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(),
                new ArrayList<>(usuario.getPesquisas().stream().map(ListagemPesquisaDTO::new).toList()));
    }

}
