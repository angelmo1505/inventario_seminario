package co.edu.uniajc.controller;

import co.edu.uniajc.model.ProveedorModel;
import co.edu.uniajc.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {
    private final ProveedorService service;

    @Autowired
    public ProveedorController(ProveedorService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProveedorModel> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ProveedorModel> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ProveedorModel create(@RequestBody ProveedorModel proveedor) {
        return service.create(proveedor);
    }

    @PutMapping
    public ProveedorModel update(@RequestBody ProveedorModel proveedor) {
        return service.update(proveedor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
