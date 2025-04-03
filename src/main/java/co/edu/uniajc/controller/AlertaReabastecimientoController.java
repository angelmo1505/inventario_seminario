package co.edu.uniajc.controller;

import co.edu.uniajc.model.AlertaReabastecimientoModel;
import co.edu.uniajc.service.AlertaReabastecimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alertas")
public class AlertaReabastecimientoController {
    private final AlertaReabastecimientoService service;

    @Autowired
    public AlertaReabastecimientoController(AlertaReabastecimientoService service) {
        this.service = service;
    }
   
    @GetMapping
    public ResponseEntity<Page<AlertaReabastecimientoModel>> findAll(
            @RequestParam(required = false) String fecha,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String usuario,
            @RequestParam(required = false) String criticidad,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<AlertaReabastecimientoModel> alertas = service.findWithFilters(fecha, categoria, estado, usuario, criticidad, page, size);

        if (alertas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(alertas);
    }

    @PostMapping
    public AlertaReabastecimientoModel create(@RequestBody AlertaReabastecimientoModel alerta) {
        return service.create(alerta);
    }
}