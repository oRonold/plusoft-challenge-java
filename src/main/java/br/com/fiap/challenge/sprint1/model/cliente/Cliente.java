package br.com.fiap.challenge.sprint1.model.cliente;

import br.com.fiap.challenge.sprint1.model.cliente.dto.AtualizarClienteDTO;
import br.com.fiap.challenge.sprint1.model.cliente.dto.CadastrarClienteDTO;
import br.com.fiap.challenge.sprint1.model.endereco.EnderecoCliente;
import br.com.fiap.challenge.sprint1.model.usuario.Usuario;
import br.com.fiap.challenge.sprint1.model.ramo.Ramo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "INOV_TB_CLIENTE")
@SequenceGenerator(name = "inov_cliente_seq", sequenceName = "inov_tb_cliente_seq", allocationSize = 1, initialValue = 1)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inov_cliente_seq")
    private Long codigo;
    @Column(name = "nm_cliente", length = 100, nullable = false)
    private String nome;
    @Column(name = "dt_nascimento", nullable = false)
    private LocalDate dataNascimento;
    @Column(name = "nr_cpf", length = 11, nullable = false)
    private String cpf;
    @Column(name = "nr_telefone", length = 15, nullable = false)
    private String telefone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cd_usuario")
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cd_ramo")
    private Ramo ramo;

    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
    private List<EnderecoCliente> enderecoClientes;

    public Cliente(CadastrarClienteDTO dto){
        this.nome = dto.nome();
        this.dataNascimento = dto.dataNascimento();
        this.cpf = dto.cpf();
        this.telefone = dto.telefone();
        enderecoClientes = new ArrayList<>();
    }

    public void atualizar(AtualizarClienteDTO dto){
        if(dto.nome() != null){
            this.nome = dto.nome();
        }
        if(dto.telefone() != null){
            this.telefone = dto.telefone();
        }
    }

}
