package br.com.fiap.challenge.sprint1.model.endereco;

import br.com.fiap.challenge.sprint1.model.cliente.Cliente;
import br.com.fiap.challenge.sprint1.model.endereco.dto.AtualizarEnderecoClienteDTO;
import br.com.fiap.challenge.sprint1.model.endereco.dto.CriarEnderecoClienteDTO;
import br.com.fiap.challenge.sprint1.model.endereco.logradouro.Logradouro;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

@Entity
@Table(name = "INOV_TB_ENDERECO_CLIENTE")
@SequenceGenerator(name = "inov_endereco_cliente_seq", sequenceName = "inov_tb_endereco_cliente_seq", allocationSize = 1)
public class EnderecoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inov_endereco_cliente_seq")
    @Column(name = "cd_endereco")
    private Long codigo;
    @Column(name = "nr_logradouro", precision = 7, nullable = false)
    private Long numero;
    @Column(name = "ds_ponto_referencia", length = 50)
    private String pontoReferencia;

    @ManyToOne
    @JoinColumn(name = "cd_cliente")
    private Cliente cliente;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cd_logradouro")
    private Logradouro logradouro;

    public EnderecoCliente(CriarEnderecoClienteDTO dto){
        this.numero = dto.numero();
        this.pontoReferencia = dto.pontoReferencia();
    }

    public void atualizar(AtualizarEnderecoClienteDTO dto){
        if(dto.numero() != null){
            this.numero = dto.numero();
        }
        if(dto.pontoReferencia() != null){
            this.pontoReferencia = dto.pontoReferencia();
        }
    }

}
