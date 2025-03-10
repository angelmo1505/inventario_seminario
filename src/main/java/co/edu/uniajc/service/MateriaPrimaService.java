package co.edu.uniajc.service;

import co.edu.uniajc.model.MateriaPrimaModel;
import co.edu.uniajc.repository.MateriaPrimaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaPrimaService {
    private final MateriaPrimaRepository repository;

    @Autowired
    public MateriaPrimaService(MateriaPrimaRepository repository) {
        this.repository = repository;
    }

    public List<MateriaPrimaModel> findAll() {
        return repository.findAll();
    }

    public Optional<MateriaPrimaModel> findById(Long id) {
        return repository.findById(id);
    }

    public MateriaPrimaModel create(MateriaPrimaModel materiaPrima) {
        return repository.save(materiaPrima);
    }

    public MateriaPrimaModel update(MateriaPrimaModel materiaPrima) {
        if (!repository.existsById(materiaPrima.getId())) {
            throw new RuntimeException("Materia Prima no encontrada");
        }
        return repository.save(materiaPrima);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Materia Prima no encontrada");
        }
        repository.deleteById(id);
    }
}
