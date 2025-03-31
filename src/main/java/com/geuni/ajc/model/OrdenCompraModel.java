package com.geuni.ajc.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ordenes_compra")
public class OrdenCompraModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "materia_prima_id", nullable = false)
    private MateriaPrimaModel materiaPrima;
    
    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)
    private ProveedorModel proveedor;
    
    @Column(nullable = false)
    private Integer cantidad;
    
    @Column(nullable = false)
    private Double precioUnitario;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoOrdenCompra estado;
    
    @Column(nullable = false)
    private LocalDateTime fechaCreacion;
    
    @Column
    private LocalDateTime fechaEstimadaEntrega;
    
    @Column
    private LocalDateTime fechaEntregaReal;
    
    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        if (estado == null) {
            estado = EstadoOrdenCompra.PENDIENTE;
        }
    }
} 