package co.edu.uniajc.repository;

import co.edu.uniajc.model.OrdenCompraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompraModel, Long> {}
