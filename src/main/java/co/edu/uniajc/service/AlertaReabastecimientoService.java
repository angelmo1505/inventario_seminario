package co.edu.uniajc.service;

import co.edu.uniajc.model.AlertaReabastecimientoModel;
import co.edu.uniajc.repository.AlertaReabastecimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlertaReabastecimientoService {
    private final AlertaReabastecimientoRepository repository;

    @Autowired
    public AlertaReabastecimientoService(AlertaReabastecimientoRepository repository) {
        this.repository = repository;
    }

    public List<AlertaReabastecimientoModel> findAll() {
        return repository.findAll();
    }

    public AlertaReabastecimientoModel create(AlertaReabastecimientoModel alerta) {
        return repository.save(alerta);
    }
}
