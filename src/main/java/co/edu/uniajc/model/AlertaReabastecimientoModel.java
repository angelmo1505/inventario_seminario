package co.edu.uniajc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class AlertaReabastecimientoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String areaSolicitante;
    private String descripcion;
    private LocalDateTime fecha;

    private String categoria;
    private String estado;
    private String usuario;
    private String criticidad;

    @ManyToOne
    @JoinColumn(name = "orden_compra_id")
    private OrdenCompraModel ordenCompra;
}