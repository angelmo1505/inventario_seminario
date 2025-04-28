package co.edu.uniajc.service;

import co.edu.uniajc.model.AlertaReabastecimientoModel;
import co.edu.uniajc.repository.AlertaReabastecimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class AlertaReabastecimientoService {
    private final AlertaReabastecimientoRepository repository;

    @Autowired
    public AlertaReabastecimientoService(AlertaReabastecimientoRepository repository) {
        this.repository = repository;
    }

    public Page<AlertaReabastecimientoModel> findWithFilters(
            String fecha, String categoria, String estado, String usuario, String criticidad, int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("fecha").descending());

        return repository.findByFilters(fecha, categoria, estado, usuario, criticidad, pageable);
    }

    public AlertaReabastecimientoModel create(AlertaReabastecimientoModel alerta) {
        return repository.save(alerta);
    }
}