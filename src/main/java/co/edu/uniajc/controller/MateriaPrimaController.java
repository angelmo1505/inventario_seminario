package co.edu.uniajc.controller;

import co.edu.uniajc.model.MateriaPrimaModel;
import co.edu.uniajc.service.MateriaPrimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiaprima")
public class MateriaPrimaController {
    private final MateriaPrimaService service;

    @Autowired
    public MateriaPrimaController(MateriaPrimaService service) { this.service = service; }

    @GetMapping
    public List<MateriaPrimaModel> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaPrimaModel> findById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public MateriaPrimaModel create(@RequestBody MateriaPrimaModel materiaPrima) {
        return service.create(materiaPrima);
    }

    @PutMapping
    public MateriaPrimaModel update(@RequestBody MateriaPrimaModel materiaPrima) {
        return service.update(materiaPrima);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
