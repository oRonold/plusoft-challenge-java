package br.com.fiap.challenge.sprint1.repository;

import br.com.fiap.challenge.sprint1.model.pesquisa.Pesquisa;
import br.com.fiap.challenge.sprint1.model.pesquisa.dto.ListagemPesquisaDTO;
import br.com.fiap.challenge.sprint1.model.usuario.Usuario;
import lombok.extern.java.Log;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PesquisaRepository extends JpaRepository<Pesquisa, Long> {

    List<Pesquisa> findAllByUsuario(Usuario usuario);

    Pesquisa findByCodigoAndUsuario(Long id, Usuario usuario);

}
