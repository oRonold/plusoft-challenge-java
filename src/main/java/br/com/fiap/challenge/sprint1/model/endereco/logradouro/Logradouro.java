package br.com.fiap.challenge.sprint1.model.endereco.logradouro;

import br.com.fiap.challenge.sprint1.model.endereco.EnderecoCliente;
import br.com.fiap.challenge.sprint1.model.endereco.bairro.Bairro;
import br.com.fiap.challenge.sprint1.model.endereco.logradouro.dto.AtualizarLogradouroDTO;
import br.com.fiap.challenge.sprint1.model.endereco.logradouro.dto.CriarLogradouroDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor

@Entity
@Table(name = "INOV_TB_LOGRADOURO")
@SequenceGenerator(name = "inov_logradouro_seq", sequenceName = "inov_tb_logradourou_seq", allocationSize = 1)
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

    @OneToMany(mappedBy = "logradouro", cascade = CascadeType.ALL)
    private List<EnderecoCliente> enderecoClientes;

    public Logradouro(CriarLogradouroDTO dto){
        this.nome = dto.nome();
        this.cep = dto.cep();
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
