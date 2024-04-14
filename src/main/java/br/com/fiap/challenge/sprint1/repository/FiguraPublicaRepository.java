package br.com.fiap.challenge.sprint1.repository;

import br.com.fiap.challenge.sprint1.model.figuraPublica.FiguraPublica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FiguraPublicaRepository extends JpaRepository<FiguraPublica,Long> {
}
