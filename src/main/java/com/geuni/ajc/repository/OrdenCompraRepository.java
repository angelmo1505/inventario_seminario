package com.geuni.ajc.repository;

import com.geuni.ajc.model.OrdenCompraModel;
import com.geuni.ajc.model.EstadoOrdenCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface OrdenCompraRepository extends JpaRepository<OrdenCompraModel, Long> {
    
    List<OrdenCompraModel> findByEstado(EstadoOrdenCompra estado);
    
    List<OrdenCompraModel> findByProveedorId(Long proveedorId);
    
    @Query("SELECT o FROM OrdenCompraModel o WHERE o.estado = :estado AND o.proveedor.id = :proveedorId")
    List<OrdenCompraModel> findByEstadoAndProveedorId(
        @Param("estado") EstadoOrdenCompra estado,
        @Param("proveedorId") Long proveedorId
    );
    
    @Query("SELECT o FROM OrdenCompraModel o WHERE o.fechaCreacion BETWEEN :fechaInicio AND :fechaFin")
    List<OrdenCompraModel> findByFechaCreacionBetween(
        @Param("fechaInicio") LocalDateTime fechaInicio,
        @Param("fechaFin") LocalDateTime fechaFin
    );
    
    @Query("SELECT o FROM OrdenCompraModel o WHERE o.materiaPrima.stockActual <= o.materiaPrima.stockMinimo")
    List<OrdenCompraModel> findOrdenesCompraNecesarias();
} 