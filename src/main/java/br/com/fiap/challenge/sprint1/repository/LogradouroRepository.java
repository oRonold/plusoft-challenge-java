package br.com.fiap.challenge.sprint1.repository;

import br.com.fiap.challenge.sprint1.model.endereco.logradouro.Logradouro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogradouroRepository extends JpaRepository<Logradouro, Long> {
}
