package br.com.fiap.challenge.sprint1.model.figuraPublica;

import br.com.fiap.challenge.sprint1.model.figuraPublica.dto.AtualizarFiguraDTO;
import br.com.fiap.challenge.sprint1.model.figuraPublica.dto.CriarFiguraDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.Pesquisa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor

@Entity
@Table(name = "INOV_TB_FIG_PUBLICA")
public class FiguraPublica {

    @Id
    @GeneratedValue
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

    public FiguraPublica(CriarFiguraDTO dto){
        this.nome = dto.nome();
        this.nomeArtistico = dto.nomeArtistico();
        this.nomeRedeSocial = dto.nomeRedeSocial();
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
