package co.edu.uniajc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    private String estado; // "Pendiente", "Completado", "Retrasado"
}