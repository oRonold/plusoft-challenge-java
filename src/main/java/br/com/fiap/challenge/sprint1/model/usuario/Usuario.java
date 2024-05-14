package br.com.fiap.challenge.sprint1.model.usuario;

import br.com.fiap.challenge.sprint1.model.pesquisa.Pesquisa;
import br.com.fiap.challenge.sprint1.model.usuario.dto.AtualizarUsuarioDTO;
import br.com.fiap.challenge.sprint1.model.usuario.dto.CriarUsuarioDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor

@Entity
@Table(name = "INOV_TB_USUARIO")
@SequenceGenerator(name = "inov_usuario_seq", sequenceName = "inov_tb_usuario_seq", allocationSize = 1)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inov_usuario_seq")
    private Long codigo;
    @Column(name = "nm_usuario", length = 100, nullable = false)
    private String nome;
    @Column(name = "ds_email", length = 100, nullable = false, unique = true)
    private String email;
    @Column(name = "ds_senha", length = 100, nullable = false)
    private String senha;

    @OneToMany(mappedBy = "usuario")
    private List<Pesquisa> pesquisas;

    public Usuario(CriarUsuarioDTO dto){
        this.nome = dto.nome();
        this.email = dto.email();
        this.senha = dto.senha();
    }

    public void atualizar(AtualizarUsuarioDTO dto){
        if(dto.nome() != null){
            this.nome = dto.nome();
        }
        if(dto.email() != null){
            this.email = dto.email();
        }
        if(dto.senha() != null){
            this.senha = dto.senha();
        }
    }

}
