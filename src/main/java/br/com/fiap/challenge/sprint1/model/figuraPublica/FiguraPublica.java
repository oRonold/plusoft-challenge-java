package br.com.fiap.challenge.sprint1.model.figuraPublica;

import br.com.fiap.challenge.sprint1.model.categoria.Categoria;
import br.com.fiap.challenge.sprint1.model.figuraPublica.dto.AtualizarFiguraDTO;
import br.com.fiap.challenge.sprint1.model.figuraPublica.dto.CriarFiguraDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.Pesquisa;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.AdicionarFigPublicaDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.CriarPesquisaDTO;
import br.com.fiap.challenge.sprint1.model.score.Score;
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
@Table(name = "INOV_TB_FIG_PUBLICA")
@SequenceGenerator(name = "inov_fig_publica_seq", sequenceName = "inov_tb_fig_publica_seq", allocationSize = 1, initialValue = 1)
public class FiguraPublica {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inov_fig_publica_seq")
    @Column(name = "cd_fig_publica")
    private Long codigo;
    @Column(name = "nm_fig_publica", length = 100, nullable = false)
    private String nome;
    @Column(name = "nm_artistico", length = 100)
    private String nomeArtistico;
    @Column(name = "nm_usuario_rede_social", length = 100, nullable = false)
    private String nomeRedeSocial;

    @ManyToMany(mappedBy = "figuraPublica", cascade = CascadeType.ALL)
    private List<Pesquisa> pesquisa;

    @OneToOne(mappedBy = "figuraPublica",cascade = CascadeType.ALL)
    private Score score;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cd_categoria")
    private Categoria categoria;

    public FiguraPublica(CriarPesquisaDTO dto){
        this.nome = dto.nomeFigPublica();
        this.nomeArtistico = dto.nomeArtistico();
        this.nomeRedeSocial = dto.usuarioRedeSocial();
        pesquisa = new ArrayList<>();
        score = new Score(dto);
        score.setFiguraPublica(this);
    }

    public FiguraPublica(AdicionarFigPublicaDTO dto) {
        this.nome = dto.nomeFigPublica();
        this.nomeArtistico = dto.nomeArtistico();
        this.nomeRedeSocial = dto.usuarioRedeSocial();
        score = new Score(dto);
        score.setFiguraPublica(this);
    }

    public void atualizar(AtualizarFiguraDTO dto){
        if(dto.nome() != null){
            this.nome = dto.nome();
        }
        if(dto.nomeArtistico() != null){
            this.nomeArtistico = dto.nomeArtistico();
        }
        if(dto.nomeRedeSocial() != null){
            this.nomeRedeSocial = dto.nomeRedeSocial();
        }
    }

}
