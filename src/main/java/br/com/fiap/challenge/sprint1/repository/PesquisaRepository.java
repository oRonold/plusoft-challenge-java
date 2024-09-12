package br.com.fiap.challenge.sprint1.repository;

import br.com.fiap.challenge.sprint1.model.pesquisa.Pesquisa;
import br.com.fiap.challenge.sprint1.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PesquisaRepository extends JpaRepository<Pesquisa, Long> {

    List<Pesquisa> findAllByUsuario(Usuario usuario);

    @Query("select p from Pesquisa p where p.statusPesquisa = 'EM_ANDAMENTO' and p.usuario = :usuario")
    List<Pesquisa> queryPesquisaEmAndamento(@Param("usuario") Usuario usuario);

    Pesquisa findByCodigoAndUsuario(Long id, Usuario usuario);

}
