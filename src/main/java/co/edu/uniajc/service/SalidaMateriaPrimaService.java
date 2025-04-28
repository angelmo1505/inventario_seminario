package co.edu.uniajc.service;

import co.edu.uniajc.model.MateriaPrimaModel;
import co.edu.uniajc.model.SalidaMateriaPrimaModel;
import co.edu.uniajc.repository.MateriaPrimaRepository;
import co.edu.uniajc.repository.SalidaMateriaPrimaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalidaMateriaPrimaService {
    private final SalidaMateriaPrimaRepository repository;
    private final MateriaPrimaRepository materiaPrimaRepository;

    @Autowired
    public SalidaMateriaPrimaService(SalidaMateriaPrimaRepository repository, MateriaPrimaRepository materiaPrimaRepository) {
        this.repository = repository;
        this.materiaPrimaRepository = materiaPrimaRepository;
    }

    public List<SalidaMateriaPrimaModel> findAll() {
        return repository.findAll();
    }

    public Optional<SalidaMateriaPrimaModel> findById(Long id) {
        return repository.findById(id);
    }

    public SalidaMateriaPrimaModel create(SalidaMateriaPrimaModel salida) {
        
        MateriaPrimaModel materiaPrima = materiaPrimaRepository.findById(salida.getMateriaPrima().getId())
                .orElseThrow(() -> new RuntimeException("Materia prima no encontrada"));

        salida.setMateriaPrima(materiaPrima);

        return repository.save(salida);
    }

    public SalidaMateriaPrimaModel update(SalidaMateriaPrimaModel salida) {
        if (!repository.existsById(salida.getId())) {
            throw new RuntimeException("Salida no encontrada");
        }
        return repository.save(salida);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Salida no encontrada");
        }
        repository.deleteById(id);
    }
}