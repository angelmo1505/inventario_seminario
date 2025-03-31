package co.edu.uniajc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class OrdenCompraModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private ProveedorModel proveedor;

    private String estado; // Pendiente, Aprobada, En Tránsito, Recibida
    private LocalDateTime fechaCreacion;
    private String estimacionEntrega;
}


