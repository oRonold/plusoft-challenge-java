package br.com.fiap.challenge.sprint1.repository;

import br.com.fiap.challenge.sprint1.model.pesquisa.Pesquisa;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PesquisaRepository extends JpaRepository<Pesquisa, Long> {
}
