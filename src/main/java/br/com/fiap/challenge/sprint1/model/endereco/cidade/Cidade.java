package br.com.fiap.challenge.sprint1.model.endereco.cidade;

import br.com.fiap.challenge.sprint1.model.endereco.bairro.Bairro;
import br.com.fiap.challenge.sprint1.model.endereco.cidade.dto.AtualizarCidadeDTO;
import br.com.fiap.challenge.sprint1.model.endereco.cidade.dto.CriarCidadeDTO;
import br.com.fiap.challenge.sprint1.model.endereco.estado.Estado;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor

@Entity
@Table(name = "INOV_TB_CIDADE")
public class Cidade {

    @Id
    @GeneratedValue
    @Column(name = "cd_cidade")
    private Long codigo;
    @Column(name = "nm_cidade", length = 100, nullable = false)
    private String nome;
    @Column(name = "nr_ddd", length = 5, nullable = false)
    private String ddd;

    @ManyToOne
    @JoinColumn(name = "cd_estado")
    private Estado estado;

    @OneToMany(mappedBy = "cidade", cascade = CascadeType.ALL)
    private List<Bairro> bairros;

    public Cidade(CriarCidadeDTO dto){
        this.nome = dto.nome();
        this.ddd = dto.ddd();
    }

    public void atualizar(AtualizarCidadeDTO dto){
        if(dto.nome() != null){
            this.nome = dto.nome();
        }
    }

}
