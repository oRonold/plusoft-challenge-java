package br.com.fiap.challenge.sprint1.model.usuario;

import br.com.fiap.challenge.sprint1.model.cliente.Cliente;
import br.com.fiap.challenge.sprint1.model.cliente.dto.CadastrarClienteDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.Pesquisa;
import br.com.fiap.challenge.sprint1.model.usuario.dto.AtualizarUsuarioDTO;
import br.com.fiap.challenge.sprint1.model.usuario.dto.CriarUsuarioDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "INOV_TB_USUARIO")
@SequenceGenerator(name = "inov_usuario_seq", sequenceName = "inov_tb_usuario_seq", allocationSize = 1, initialValue = 1)
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

    @OneToOne(mappedBy = "usuario")
    private Cliente cliente;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Pesquisa> pesquisas;

    public Usuario(CadastrarClienteDTO dto){
        this.nome = dto.nome();
        this.email = dto.email();
        this.senha = dto.senha();
        pesquisas = new ArrayList<>();
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
