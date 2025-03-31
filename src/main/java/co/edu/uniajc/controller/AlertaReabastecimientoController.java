package co.edu.uniajc.controller;

import co.edu.uniajc.model.AlertaReabastecimientoModel;
import co.edu.uniajc.service.AlertaReabastecimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alertas")
public class AlertaReabastecimientoController {
    private final AlertaReabastecimientoService service;

    @Autowired
    public AlertaReabastecimientoController(AlertaReabastecimientoService service) {
        this.service = service;
    }

    @GetMapping
    public List<AlertaReabastecimientoModel> findAll() {
        return service.findAll();
    }

    @PostMapping
    public AlertaReabastecimientoModel create(@RequestBody AlertaReabastecimientoModel alerta) {
        return service.create(alerta);
    }
}

