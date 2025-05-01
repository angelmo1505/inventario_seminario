package co.edu.uniajc.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "salidamateriaprima")
public class SalidaMateriaPrimaModel {
    @Id
    @GeneratedValue(strategy = GenerationType
            .IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "materia_prima_id")
    private MateriaPrimaModel materiaPrima;

    private int cantidad;
    private LocalDate fecha;
}
