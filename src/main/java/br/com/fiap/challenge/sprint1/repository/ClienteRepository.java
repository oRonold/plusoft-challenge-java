package br.com.fiap.challenge.sprint1.repository;

import br.com.fiap.challenge.sprint1.model.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
