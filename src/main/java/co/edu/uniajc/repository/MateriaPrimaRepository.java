package co.edu.uniajc.repository;

import co.edu.uniajc.model.MateriaPrimaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaPrimaRepository extends JpaRepository<MateriaPrimaModel, Long> {}
