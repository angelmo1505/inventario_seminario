package co.edu.uniajc.controller;

import co.edu.uniajc.model.DistribucionModel;
import co.edu.uniajc.service.DistribucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/distribucion")
public class DistribucionController {
    private final DistribucionService service;

    @Autowired
    public DistribucionController(DistribucionService service) {
        this.service = service;
    }

    @GetMapping
    public List<DistribucionModel> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DistribucionModel> findById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DistribucionModel create(@RequestBody DistribucionModel distribucion) {
        return service.create(distribucion);
    }

    @PutMapping
    public DistribucionModel update(@RequestBody DistribucionModel distribucion) {
        return service.update(distribucion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
