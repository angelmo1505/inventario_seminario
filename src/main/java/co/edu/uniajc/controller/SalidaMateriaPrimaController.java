package co.edu.uniajc.controller;

import co.edu.uniajc.model.SalidaMateriaPrimaModel;
import co.edu.uniajc.service.SalidaMateriaPrimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salidamateriaprima")
public class SalidaMateriaPrimaController {
    private final SalidaMateriaPrimaService service;

    @Autowired
    public SalidaMateriaPrimaController(SalidaMateriaPrimaService service) {
        this.service = service;
    }

    @GetMapping
    public List<SalidaMateriaPrimaModel> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalidaMateriaPrimaModel> findById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public SalidaMateriaPrimaModel create(@RequestBody SalidaMateriaPrimaModel salida) {
        return service.create(salida);
    }

    @PutMapping
    public SalidaMateriaPrimaModel update(@RequestBody SalidaMateriaPrimaModel salida) {
        return service.update(salida); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
