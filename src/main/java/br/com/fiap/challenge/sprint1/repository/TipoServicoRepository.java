package br.com.fiap.challenge.sprint1.repository;

import br.com.fiap.challenge.sprint1.model.tipoServico.TipoServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoServicoRepository extends JpaRepository<TipoServico, Long> {
}
