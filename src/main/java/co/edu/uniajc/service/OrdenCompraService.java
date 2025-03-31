package co.edu.uniajc.service;

import co.edu.uniajc.model.OrdenCompraModel;
import co.edu.uniajc.repository.OrdenCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenCompraService {
    private final OrdenCompraRepository repository;

    @Autowired
    public OrdenCompraService(OrdenCompraRepository repository) {
        this.repository = repository;
    }
    public List<OrdenCompraModel> findAll() {
        return repository.findAll();
    }
    public Optional<OrdenCompraModel> findById(Long id) {
        return repository.findById(id);
    }
    public OrdenCompraModel create(OrdenCompraModel ordenCompra) {
        return repository.save(ordenCompra);
    }
    public OrdenCompraModel update(OrdenCompraModel ordenCompra) {
        if (!repository.existsById(ordenCompra.getId())) {
            throw new RuntimeException("Orden de compra no encontrada");
        }
        return repository.save(ordenCompra);
    }
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Orden de compra no encontrada");
        }
        repository.deleteById(id);
    }
}
