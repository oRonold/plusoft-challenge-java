package br.com.fiap.challenge.sprint1.model.usuario.dto;

import br.com.fiap.challenge.sprint1.model.usuario.Usuario;

public record PublicListaUsuarioDTO(Long id, String nome, String email) {

    public PublicListaUsuarioDTO(Usuario usuario){
        this(usuario.getCodigo(), usuario.getNome(), usuario.getEmail());
    }
}
