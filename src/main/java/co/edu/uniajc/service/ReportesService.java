package co.edu.uniajc.service;

import co.edu.uniajc.model.ReportesModel;
import co.edu.uniajc.repository.ReportesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportesService {
    private final ReportesRepository repository;
    @Autowired
    public ReportesService(ReportesRepository repository) {
        this.repository = repository;
    }
    public List<ReportesModel> findAll() {
        return repository.findAll();
    }
    public Optional<ReportesModel> findById(Long id) {
        return repository.findById(id);
    }
    public ReportesModel create(ReportesModel reporte) {
        return repository.save(reporte);
    }
    public ReportesModel update(ReportesModel reporte) {
        if (!repository.existsById(reporte.getId())) {
            throw new RuntimeException("distribucion no encontrada");
        }
        return repository.save(reporte);
    }
    public void delete(Long id) {
        if (!repository.existsById(id)) { throw new RuntimeException("Reporte no encontrado"); }
        repository.deleteById(id);
    }
}
