package br.com.fiap.challenge.sprint1.repository;

import br.com.fiap.challenge.sprint1.model.endereco.bairro.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BairroRepository extends JpaRepository<Bairro, Long> {
}
