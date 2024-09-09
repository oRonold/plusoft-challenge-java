package br.com.fiap.challenge.sprint1.model.usuario;

import br.com.fiap.challenge.sprint1.model.cliente.Cliente;
import br.com.fiap.challenge.sprint1.model.cliente.dto.CadastrarClienteDTO;
import br.com.fiap.challenge.sprint1.model.pesquisa.Pesquisa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "INOV_TB_USUARIO")
@SequenceGenerator(name = "inov_usuario_seq", sequenceName = "inov_tb_usuario_seq", allocationSize = 1, initialValue = 1)
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inov_usuario_seq")
    @Column(name = "cd_usuario")
    private Long codigo;
    @Column(name = "nm_usuario", length = 100, nullable = false)
    private String nome;
    @Column(name = "ds_email", length = 100, nullable = false, unique = true)
    private String email;
    @Column(name = "ds_senha", length = 100, nullable = false)
    private String senha;

    @OneToOne(mappedBy = "usuario")
    private Cliente cliente;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Pesquisa> pesquisas;

    public Usuario(CadastrarClienteDTO dto){
        this.nome = dto.nome();
        this.email = dto.email();
        this.senha = dto.senha();
        pesquisas = new ArrayList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
