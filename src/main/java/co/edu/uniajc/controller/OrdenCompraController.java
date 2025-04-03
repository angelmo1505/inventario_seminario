package co.edu.uniajc.controller;

import co.edu.uniajc.model.OrdenCompraModel;
import co.edu.uniajc.service.OrdenCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordencompra")
public class OrdenCompraController {
    private final OrdenCompraService service;

    @Autowired
    public OrdenCompraController(OrdenCompraService service) {
        this.service = service;
    }

    @GetMapping
    public List<OrdenCompraModel> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenCompraModel> findById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody OrdenCompraModel ordenCompra) {
        try {
            OrdenCompraModel nuevaOrden = service.create(ordenCompra);
            return ResponseEntity.ok(nuevaOrden);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public OrdenCompraModel update(@RequestBody OrdenCompraModel ordenCompra) {
        return service.update(ordenCompra);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    //  Actualizar estado de una orden
    @PutMapping("/actualizar-estado/{id}")
    public ResponseEntity<OrdenCompraModel> actualizarEstadoOrden(
            @PathVariable Long id, @RequestParam String estado) {
        return ResponseEntity.ok(service.actualizarEstadoOrden(id, estado));
    }

    //  Consultar reporte de órdenes
    @GetMapping("/reporte")
    public List<OrdenCompraModel> obtenerReporteOrdenesCompra() {
        return service.obtenerReporteOrdenesCompra();
    }
}

