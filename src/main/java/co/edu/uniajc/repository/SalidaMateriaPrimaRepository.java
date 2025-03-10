package co.edu.uniajc.repository;

import co.edu.uniajc.model.SalidaMateriaPrimaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalidaMateriaPrimaRepository extends JpaRepository<SalidaMateriaPrimaModel, Long> {}
