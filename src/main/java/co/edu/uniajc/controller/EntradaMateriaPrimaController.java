package co.edu.uniajc.controller;

import co.edu.uniajc.model.EntradaMateriaPrimaModel;
import co.edu.uniajc.service.EntradaMateriaPrimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entradamateriaprima")
public class EntradaMateriaPrimaController {
    private final EntradaMateriaPrimaService service;

    @Autowired
    public EntradaMateriaPrimaController(EntradaMateriaPrimaService service) { this.service = service; }

    @GetMapping
    public List<EntradaMateriaPrimaModel> findAll() {
        return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<EntradaMateriaPrimaModel> findById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EntradaMateriaPrimaModel create(@RequestBody EntradaMateriaPrimaModel entrada) {
        return service.create(entrada);
    }

    @PutMapping
    public EntradaMateriaPrimaModel update(@RequestBody EntradaMateriaPrimaModel entrada) {
        return service.update(entrada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
