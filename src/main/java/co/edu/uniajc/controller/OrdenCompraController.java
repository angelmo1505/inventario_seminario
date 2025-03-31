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
    public OrdenCompraModel crear(@RequestBody OrdenCompraModel ordenCompra) {
        return service.create(ordenCompra);
    }

    @PutMapping
    public OrdenCompraModel update(@RequestBody OrdenCompraModel ordenCompra) {
        return service.update(ordenCompra);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    //  Generar orden de compra automática
    @PostMapping("/generar-orden")
    public ResponseEntity<OrdenCompraModel> generarOrdenAutomatica(
            @RequestParam String producto, @RequestParam int cantidadMinima, @RequestParam int cantidadActual) {
        return ResponseEntity.ok(service.generarOrdenAutomatica(producto, cantidadMinima, cantidadActual));
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

