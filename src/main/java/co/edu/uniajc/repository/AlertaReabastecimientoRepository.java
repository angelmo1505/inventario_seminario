package co.edu.uniajc.repository;

import co.edu.uniajc.model.AlertaReabastecimientoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertaReabastecimientoRepository extends JpaRepository<AlertaReabastecimientoModel, Long> {}

