package br.com.fiap.challenge.sprint1.repository;

import br.com.fiap.challenge.sprint1.model.endereco.estado.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
