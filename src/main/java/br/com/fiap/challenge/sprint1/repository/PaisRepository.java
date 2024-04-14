package br.com.fiap.challenge.sprint1.repository;

import br.com.fiap.challenge.sprint1.model.endereco.pais.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository extends JpaRepository<Pais, Long> {
}
