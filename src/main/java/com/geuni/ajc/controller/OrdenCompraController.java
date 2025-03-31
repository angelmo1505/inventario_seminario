package com.geuni.ajc.controller;

import com.geuni.ajc.model.OrdenCompraModel;
import com.geuni.ajc.model.EstadoOrdenCompra;
import com.geuni.ajc.service.OrdenCompraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/ordenes-compra")
@Tag(name = "Órdenes de Compra", description = "API para gestionar órdenes de compra de materia prima")
public class OrdenCompraController {

    @Autowired
    private OrdenCompraService ordenCompraService;

    @PostMapping
    @Operation(summary = "Crear una nueva orden de compra")
    public ResponseEntity<OrdenCompraModel> crearOrdenCompra(@RequestBody OrdenCompraModel ordenCompra) {
        return ResponseEntity.ok(ordenCompraService.crearOrdenCompra(ordenCompra));
    }

    @PutMapping("/{id}/estado")
    @Operation(summary = "Actualizar el estado de una orden de compra")
    public ResponseEntity<OrdenCompraModel> actualizarEstadoOrden(
            @Parameter(description = "ID de la orden de compra") @PathVariable Long id,
            @Parameter(description = "Nuevo estado de la orden") @RequestParam EstadoOrdenCompra estado) {
        return ResponseEntity.ok(ordenCompraService.actualizarEstadoOrden(id, estado));
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Obtener órdenes de compra por estado")
    public ResponseEntity<List<OrdenCompraModel>> obtenerOrdenesPorEstado(
            @Parameter(description = "Estado de las órdenes") @PathVariable EstadoOrdenCompra estado) {
        return ResponseEntity.ok(ordenCompraService.obtenerOrdenesPorEstado(estado));
    }

    @GetMapping("/proveedor/{proveedorId}")
    @Operation(summary = "Obtener órdenes de compra por proveedor")
    public ResponseEntity<List<OrdenCompraModel>> obtenerOrdenesPorProveedor(
            @Parameter(description = "ID del proveedor") @PathVariable Long proveedorId) {
        return ResponseEntity.ok(ordenCompraService.obtenerOrdenesPorProveedor(proveedorId));
    }

    @GetMapping("/fecha")
    @Operation(summary = "Obtener órdenes de compra por rango de fechas")
    public ResponseEntity<List<OrdenCompraModel>> obtenerOrdenesPorFecha(
            @Parameter(description = "Fecha de inicio") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @Parameter(description = "Fecha de fin") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
        return ResponseEntity.ok(ordenCompraService.obtenerOrdenesPorFecha(fechaInicio, fechaFin));
    }
} 