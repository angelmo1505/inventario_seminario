package co.edu.uniajc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class EntradaMateriaPrimaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "materia_prima_id")
    private MateriaPrimaModel materiaPrima;

    private int cantidad;
    private String fechaIngreso;
}
