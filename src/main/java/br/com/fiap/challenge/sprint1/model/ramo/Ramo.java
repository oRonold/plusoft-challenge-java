package br.com.fiap.challenge.sprint1.model.ramo;

import br.com.fiap.challenge.sprint1.model.cliente.Cliente;
import br.com.fiap.challenge.sprint1.model.cliente.dto.CadastrarClienteDTO;
import br.com.fiap.challenge.sprint1.model.ramo.dto.AtualizarRamoDTO;
import br.com.fiap.challenge.sprint1.model.ramo.dto.CriarRamoDTO;
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
@Table(name = "INOV_TB_RAMO")
@SequenceGenerator(name = "inov_ramo_seq", sequenceName = "inov_tb_ramo_seq", allocationSize = 1, initialValue = 1)
public class Ramo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inov_ramo_seq")
    private Long codigo;
    @Column(name = "nm_ramo", length = 100, nullable = false)
    private String nome;
    @Column(name = "ds_ramo", length = 100, nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "ramo")
    private List<Cliente> cliente;

    public Ramo(CadastrarClienteDTO dto){
        this.nome = dto.nomeRamo();
        this.descricao = dto.descRamo();
        cliente = new ArrayList<>();
    }

    public void atualizar(AtualizarRamoDTO dto){
        if(dto.nome() != null){
            this.nome = dto.nome();
        }
        if(dto.descricao() != null){
            this.descricao = dto.descricao();
        }
    }

}
