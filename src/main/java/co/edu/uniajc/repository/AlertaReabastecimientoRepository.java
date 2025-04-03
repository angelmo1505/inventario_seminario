package co.edu.uniajc.repository;

import co.edu.uniajc.model.AlertaReabastecimientoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertaReabastecimientoRepository extends JpaRepository<AlertaReabastecimientoModel, Long> {

    @Query("SELECT a FROM AlertaReabastecimientoModel a WHERE "
            + "(:fecha IS NULL OR a.fecha = :fecha) "
            + "AND (:categoria IS NULL OR a.categoria = :categoria) "
            + "AND (:estado IS NULL OR a.estado = :estado) "
            + "AND (:usuario IS NULL OR a.usuario = :usuario) "
            + "AND (:criticidad IS NULL OR a.criticidad = :criticidad)")
    Page<AlertaReabastecimientoModel> findByFilters(
            @Param("fecha") String fecha,
            @Param("categoria") String categoria,
            @Param("estado") String estado,
            @Param("usuario") String usuario,
            @Param("criticidad") String criticidad,
            Pageable pageable);
}