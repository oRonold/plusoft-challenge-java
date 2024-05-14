package br.com.fiap.challenge.sprint1.model.endereco.bairro;

import br.com.fiap.challenge.sprint1.model.endereco.bairro.dto.AtualizarBairroDTO;
import br.com.fiap.challenge.sprint1.model.endereco.bairro.dto.CriarBairroDTO;
import br.com.fiap.challenge.sprint1.model.endereco.cidade.Cidade;
import br.com.fiap.challenge.sprint1.model.endereco.logradouro.Logradouro;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor

@Entity
@Table(name = "INOV_TB_BAIRRO")
@SequenceGenerator(name = "inov_bairro_seq", sequenceName = "inov_tb_bairro_seq", allocationSize = 1)
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inov_bairro_seq")
    @Column(name = "cd_bairro")
    private Long codigo;
    @Column(name = "nm_bairro", length = 100, nullable = false)
    private String nome;
    @Column(name = "nm_zona", length = 20)
    private String zona;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cd_cidade")
    private Cidade cidade;

    @OneToMany(mappedBy = "bairro", cascade = CascadeType.ALL)
    private List<Logradouro> logradouros;

    public Bairro(CriarBairroDTO dto){
        this.nome = dto.nome();
        this.zona = dto.zona();
    }

    public void atualizar(AtualizarBairroDTO dto){
        if(dto.nome() != null){
            this.nome = dto.nome();
        }
        if(dto.zona() != null){
            this.zona = dto.zona();
        }
    }

}
