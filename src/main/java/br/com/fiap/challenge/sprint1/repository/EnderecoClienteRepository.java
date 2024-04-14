package br.com.fiap.challenge.sprint1.repository;

import br.com.fiap.challenge.sprint1.controller.EnderecoClienteController;
import br.com.fiap.challenge.sprint1.model.endereco.EnderecoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoClienteRepository extends JpaRepository<EnderecoCliente, Long> {
}
