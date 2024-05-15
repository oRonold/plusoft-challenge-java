package br.com.fiap.challenge.sprint1.model.score;

import br.com.fiap.challenge.sprint1.model.figuraPublica.FiguraPublica;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "INOV_TB_SCORE")
@SequenceGenerator(name = "inov_score_seq", sequenceName = "inov_tb_score_seq", allocationSize = 1, initialValue = 1)
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inov_score_seq")
    @Column(name = "cd_score")
    private Integer codigo;
    @Column(name = "nr_score", nullable = false, scale = 2, precision = 10)
    private BigDecimal numeroScore;

    @OneToOne
    @JoinColumn(name = "cd_fig_publica")
    private FiguraPublica figuraPublica;


}
