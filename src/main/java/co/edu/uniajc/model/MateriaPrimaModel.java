package co.edu.uniajc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class MateriaPrimaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private int stockActual;

    @OneToMany(mappedBy = "materiaPrima")
    private List<EntradaMateriaPrimaModel> entradas;

    @OneToMany(mappedBy = "materiaPrima")
    private List<SalidaMateriaPrimaModel> salidas;
}