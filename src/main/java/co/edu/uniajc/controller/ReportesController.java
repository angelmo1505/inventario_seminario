package co.edu.uniajc.controller;

import co.edu.uniajc.model.DistribucionModel;
import co.edu.uniajc.model.ReportesModel;
import co.edu.uniajc.service.ReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReportesController {
    private final ReportesService service;

    @Autowired
    public ReportesController(ReportesService service) { this.service = service; }

    @GetMapping
    public List<ReportesModel> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportesModel> findById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ReportesModel create(@RequestBody ReportesModel reporte) {
        return service.create(reporte); }

    @PutMapping
    public ReportesModel update(@RequestBody ReportesModel reporte) {
        return service.update(reporte);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
