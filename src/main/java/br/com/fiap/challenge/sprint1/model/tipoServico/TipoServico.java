package br.com.fiap.challenge.sprint1.model.tipoServico;

import br.com.fiap.challenge.sprint1.model.pesquisa.Pesquisa;
import br.com.fiap.challenge.sprint1.model.tipoServico.dto.AtualizarServicoDTO;
import br.com.fiap.challenge.sprint1.model.tipoServico.dto.CadastrarServicoDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor

@Entity
@Table(name = "INOV_TB_TIPO_SERVICO")
public class TipoServico {

    @Id
    @GeneratedValue
    @Column(name = "cd_tipo_servico")
    private Long codigo;
    @Column(name = "ds_tipo_servico", length = 100, nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "tipoServico", cascade = CascadeType.ALL)
    private List<Pesquisa> pesquisas;


    public TipoServico(CadastrarServicoDTO dto){
        this.descricao = dto.descricao();
    }

    public void atualizar(AtualizarServicoDTO dto){
        if(dto.descricao() != null){
            this.descricao = dto.descricao();
        }
    }

}
