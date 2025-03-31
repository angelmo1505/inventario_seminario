package com.geuni.ajc.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "materias_primas")
public class MateriaPrimaModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String codigo;
    
    @Column(nullable = false)
    private Integer stockActual;
    
    @Column(nullable = false)
    private Integer stockMinimo;
    
    @Column(nullable = false)
    private String unidadMedida;
    
    @Column
    private String descripcion;
    
    @Column
    private boolean activo = true;
} 