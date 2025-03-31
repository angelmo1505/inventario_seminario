package co.edu.uniajc.service;

import co.edu.uniajc.model.DistribucionModel;

import co.edu.uniajc.repository.DistribucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistribucionService {
    private final DistribucionRepository repository;
    // hola como
    @Autowired
    public DistribucionService(DistribucionRepository repository) {
        this.repository = repository;
    }
    public List<DistribucionModel> findAll() {
        return repository.findAll();
    }
    public Optional<DistribucionModel> findById(Long id) {
        return repository.findById(id);
    }
    public DistribucionModel create(DistribucionModel distribucion) {
        return repository.save(distribucion);
    }
    public DistribucionModel update(DistribucionModel distribucion) {
        if (!repository.existsById(distribucion.getId())) {
            throw new RuntimeException("distribucion no encontrada");
        }
        return repository.save(distribucion);
    }
    public void delete(Long id) {
        if (!repository.existsById(id)) { throw new RuntimeException("Distribución no encontrada"); }
        repository.deleteById(id);
    }
}
