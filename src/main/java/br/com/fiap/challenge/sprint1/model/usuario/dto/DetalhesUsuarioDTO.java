package br.com.fiap.challenge.sprint1.model.usuario.dto;

import br.com.fiap.challenge.sprint1.model.usuario.Usuario;

public record DetalhesUsuarioDTO(Long codigo, String nome, String email, String senha) {

    public DetalhesUsuarioDTO(Usuario usuario){
        this(usuario.getCodigo(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
    }

}
