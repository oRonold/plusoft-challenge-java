package br.com.fiap.challenge.sprint1.model.endereco.pais;

import br.com.fiap.challenge.sprint1.model.endereco.estado.Estado;
import br.com.fiap.challenge.sprint1.model.endereco.pais.dto.AtualizarPaisDTO;
import br.com.fiap.challenge.sprint1.model.endereco.pais.dto.CriarPaisDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor

@Entity
@Table(name = "INOV_TB_PAIS")
@SequenceGenerator(name = "inov_pais_seq", sequenceName = "inov_tb_pais_seq", allocationSize = 1)
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inov_pais_seq")
    @Column(name = "cd_pais")
    private Long codigo;
    @Column(name = "nm_pais", length = 50, nullable = false)
    private String nome;
    @Column(name = "nr_cd_pais", length = 5, nullable = false)
    private String numeroCodigo;

    @OneToMany(mappedBy = "pais", cascade = CascadeType.ALL)
    private List<Estado> estados;

    public Pais(CriarPaisDTO dto){
        this.nome = dto.nome();
        this.numeroCodigo = dto.numeroCodPais();
    }

    public void atualizar(AtualizarPaisDTO dto){
        if(dto.nome() != null){
            this.nome = dto.nome();
        }
        if(dto.codPais() != null){
            this.numeroCodigo = dto.codPais();
        }
    }

}
