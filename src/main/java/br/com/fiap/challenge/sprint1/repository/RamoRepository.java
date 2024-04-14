package br.com.fiap.challenge.sprint1.repository;

import br.com.fiap.challenge.sprint1.model.ramo.Ramo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RamoRepository extends JpaRepository<Ramo,Long> {
}
