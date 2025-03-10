package co.edu.uniajc.service;

import co.edu.uniajc.model.ProveedorModel;
import co.edu.uniajc.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {
    private final ProveedorRepository repository;

    @Autowired
    public ProveedorService(ProveedorRepository repository) {
        this.repository = repository;
    }

    public List<ProveedorModel> findAll() {
        return repository.findAll();
    }

    public Optional<ProveedorModel> findById(Long id) {
        return repository.findById(id);
    }

    public ProveedorModel create(ProveedorModel proveedor) {
        return repository.save(proveedor);
    }

    public ProveedorModel update(ProveedorModel proveedor) {
        if (!repository.existsById(proveedor.getId())) {
            throw new RuntimeException("Proveedor no encontrado");
        }
        return repository.save(proveedor);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Proveedor no encontrado");
        }
        repository.deleteById(id);
    }
}
