package br.com.fiap.challenge.sprint1.model.usuario.dto;

import br.com.fiap.challenge.sprint1.model.usuario.Usuario;

public record ListagemUsuarioDTO(Long codigo, String nome, String email, String senha) {

    public ListagemUsuarioDTO(Usuario usuario){
        this(usuario.getCodigo(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
    }

}
