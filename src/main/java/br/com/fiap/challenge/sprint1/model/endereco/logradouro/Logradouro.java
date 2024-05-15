package br.com.fiap.challenge.sprint1.model.endereco.logradouro;

import br.com.fiap.challenge.sprint1.model.cliente.dto.CadastrarClienteDTO;
import br.com.fiap.challenge.sprint1.model.endereco.EnderecoCliente;
import br.com.fiap.challenge.sprint1.model.endereco.bairro.Bairro;
import br.com.fiap.challenge.sprint1.model.endereco.logradouro.dto.AtualizarLogradouroDTO;
import br.com.fiap.challenge.sprint1.model.endereco.logradouro.dto.CriarLogradouroDTO;
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
@Table(name = "INOV_TB_LOGRADOURO")
@SequenceGenerator(name = "inov_logradouro_seq", sequenceName = "inov_tb_logradouro_seq", allocationSize = 1, initialValue = 1)
public class Logradouro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inov_logradouro_seq")
    @Column(name = "cd_logradouro")
    private Long codigo;
    @Column(name = "nm_logradouro", length = 100, nullable = false)
    private String nome;
    @Column(name = "nr_cep", length = 8, nullable = false)
    private String cep;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cd_bairro")
    private Bairro bairro;

    @OneToMany(mappedBy = "logradouro")
    private List<EnderecoCliente> enderecoClientes;

    public Logradouro(CadastrarClienteDTO dto){
        this.nome = dto.nomeLogradouro();
        this.cep = dto.cep();
        enderecoClientes = new ArrayList<>();
    }

    public void atualizar(AtualizarLogradouroDTO dto){
        if(dto.nome() != null){
            this.nome = dto.nome();
        }
        if(dto.cep() != null){
            this.cep = dto.cep();
        }
    }


}
