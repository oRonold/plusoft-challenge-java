package br.com.fiap.challenge.sprint1.model.endereco.estado;

import br.com.fiap.challenge.sprint1.controller.AtualizarEstadoDTO;
import br.com.fiap.challenge.sprint1.model.endereco.cidade.Cidade;
import br.com.fiap.challenge.sprint1.model.endereco.estado.dto.CriarEstadoDTO;
import br.com.fiap.challenge.sprint1.model.endereco.pais.Pais;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor

@Entity
@Table(name = "INOV_TB_ESTADO")
public class Estado {

    @Id
    @GeneratedValue
    @Column(name = "cd_estado")
    private Long codigo;
    @Column(name = "nm_estado", length = 50, nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "cd_pais")
    private Pais pais;

    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL)
    private List<Cidade> cidades;

    public Estado(CriarEstadoDTO dto){
        this.nome = dto.nome();
    }

    public void atualizar(AtualizarEstadoDTO dto){
        if(dto.nome() != null){
            this.nome = dto.nome();
        }
    }

}
