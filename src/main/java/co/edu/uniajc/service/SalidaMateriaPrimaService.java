package co.edu.uniajc.service;

import co.edu.uniajc.model.EntradaMateriaPrimaModel;
import co.edu.uniajc.model.SalidaMateriaPrimaModel;
import co.edu.uniajc.repository.SalidaMateriaPrimaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalidaMateriaPrimaService {
    private final SalidaMateriaPrimaRepository repository;

    @Autowired
    public SalidaMateriaPrimaService(SalidaMateriaPrimaRepository repository) {
        this.repository = repository;
    }
    public List<SalidaMateriaPrimaModel> findAll() { return repository.findAll(); }
    public Optional<SalidaMateriaPrimaModel> findById(Long id) {
        return repository.findById(id);
    }
    public SalidaMateriaPrimaModel create(SalidaMateriaPrimaModel salida) {
        return repository.save(salida);
    }
    public SalidaMateriaPrimaModel update(SalidaMateriaPrimaModel salida) {
        if (!repository.existsById(salida.getId())) {
            throw new RuntimeException("Salida no encontrada");
        }
        //
        return repository.save(salida);
    }
    public void delete(Long id) {
        if (!repository.existsById(id)) { throw new RuntimeException("Salida no encontrada"); }
        repository.deleteById(id);
    }
}
