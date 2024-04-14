package br.com.fiap.challenge.sprint1.model.ramo;

import br.com.fiap.challenge.sprint1.model.ramo.dto.AtualizarRamoDTO;
import br.com.fiap.challenge.sprint1.model.ramo.dto.CriarRamoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

@Entity
@Table(name = "INOV_TB_RAMO")
public class Ramo {

    @Id
    @GeneratedValue
    private Long codigo;
    @Column(name = "nm_ramo", length = 100, nullable = false)
    private String nome;
    @Column(name = "ds_ramo", length = 100, nullable = false)
    private String descricao;

    public Ramo(CriarRamoDTO dto){
        this.nome = dto.nome();
        this.descricao = dto.descricao();
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
