package br.com.fiap.challenge.sprint1.repository;

import br.com.fiap.challenge.sprint1.model.endereco.cidade.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
