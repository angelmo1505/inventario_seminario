package co.edu.uniajc.service;

import co.edu.uniajc.model.EntradaMateriaPrimaModel;
import co.edu.uniajc.repository.EntradaMaterialPrimaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntradaMateriaPrimaService {
    private final EntradaMaterialPrimaRepository repository;

    @Autowired
    public EntradaMateriaPrimaService(EntradaMaterialPrimaRepository repository) {
        this.repository = repository;
    }
    public List<EntradaMateriaPrimaModel> findAll() {
        return repository.findAll();
    }
    public Optional<EntradaMateriaPrimaModel> findById(Long id) {
        return repository.findById(id);
    }
    public EntradaMateriaPrimaModel create(EntradaMateriaPrimaModel entrada) {
        return repository.save(entrada);
    }
    public EntradaMateriaPrimaModel update(EntradaMateriaPrimaModel entrada) {
        if (!repository.existsById(entrada.getId())) {
            throw new RuntimeException("Entrada no encontrada");
        }
        return repository.save(entrada);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Entrada no encontrada");
        }
        repository.deleteById(id);
    }
}
