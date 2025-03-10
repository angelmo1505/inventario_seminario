package co.edu.uniajc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
public class ProveedorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String contacto;
    private String telefono;
    private String direccion;

    @OneToMany(mappedBy = "proveedor")
    private List<OrdenCompraModel> ordenesCompra;
}