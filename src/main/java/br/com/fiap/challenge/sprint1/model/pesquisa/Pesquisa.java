package br.com.fiap.challenge.sprint1.model.pesquisa;

import br.com.fiap.challenge.sprint1.model.figuraPublica.FiguraPublica;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.AtualizarPesquisaDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.CriarPesquisaDTO;
import br.com.fiap.challenge.sprint1.model.tipoServico.TipoServico;
import br.com.fiap.challenge.sprint1.model.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor

@Entity
@Table(name = "INOV_TB_PESQUISA")
@JsonIgnoreProperties(value = {"tipoServico"})
public class Pesquisa {

    @Id
    @GeneratedValue
    @Column(name = "cd_codigo")
    private Long codigo;
    @Column(name = "ds_pesquisa", length = 100, nullable = false)
    private String descricao;
    @Column(name = "dt_pesquisa", length = 100, nullable = false)
    private LocalDate dataPesquisa;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "INOV_TB_PESQUISA_FIG_PUBLICA", joinColumns = @JoinColumn(name = "cd_pesquisa", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "cd_fig_publica", nullable = false))
    private List<FiguraPublica> figuraPublica;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cd_tipo_servico")
    private TipoServico tipoServico;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cd_usuario")
    private Usuario usuario;

    public Pesquisa(CriarPesquisaDTO dto){
        this.descricao = dto.descricao();
        this.dataPesquisa = dto.dataPesquisa();
    }

    public void atualizar(AtualizarPesquisaDTO dto){
        if(dto.descricao() != null){
            this.descricao = dto.descricao();
        }
    }

}
