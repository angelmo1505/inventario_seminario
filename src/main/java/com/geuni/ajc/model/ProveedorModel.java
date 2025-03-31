package com.geuni.ajc.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "proveedores")
public class ProveedorModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String ruc;
    
    @Column
    private String direccion;
    
    @Column
    private String telefono;
    
    @Column
    private String email;
    
    @Column
    private boolean activo = true;
} 