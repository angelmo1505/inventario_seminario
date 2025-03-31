package com.geuni.ajc.service;

import com.geuni.ajc.model.OrdenCompraModel;
import com.geuni.ajc.model.EstadoOrdenCompra;
import com.geuni.ajc.repository.OrdenCompraRepository;
import com.geuni.ajc.repository.MateriaPrimaRepository;
import com.geuni.ajc.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrdenCompraService {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;
    
    @Autowired
    private MateriaPrimaRepository materiaPrimaRepository;
    
    @Autowired
    private ProveedorRepository proveedorRepository;

    @Transactional
    public OrdenCompraModel crearOrdenCompra(OrdenCompraModel ordenCompra) {
        return ordenCompraRepository.save(ordenCompra);
    }

    @Transactional
    public OrdenCompraModel actualizarEstadoOrden(Long id, EstadoOrdenCompra nuevoEstado) {
        OrdenCompraModel orden = ordenCompraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Orden de compra no encontrada"));
        
        orden.setEstado(nuevoEstado);
        if (nuevoEstado == EstadoOrdenCompra.RECIBIDA) {
            orden.setFechaEntregaReal(LocalDateTime.now());
            // Actualizar stock de materia prima
            orden.getMateriaPrima().setStockActual(
                orden.getMateriaPrima().getStockActual() + orden.getCantidad()
            );
            materiaPrimaRepository.save(orden.getMateriaPrima());
        }
        
        return ordenCompraRepository.save(orden);
    }

    public List<OrdenCompraModel> obtenerOrdenesPorEstado(EstadoOrdenCompra estado) {
        return ordenCompraRepository.findByEstado(estado);
    }

    public List<OrdenCompraModel> obtenerOrdenesPorProveedor(Long proveedorId) {
        return ordenCompraRepository.findByProveedorId(proveedorId);
    }

    public List<OrdenCompraModel> obtenerOrdenesPorFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return ordenCompraRepository.findByFechaCreacionBetween(fechaInicio, fechaFin);
    }

    @Scheduled(cron = "0 0 * * * *") // Se ejecuta cada hora
    @Transactional
    public void generarOrdenesCompraAutomaticas() {
        List<OrdenCompraModel> ordenesNecesarias = ordenCompraRepository.findOrdenesCompraNecesarias();
        
        for (OrdenCompraModel orden : ordenesNecesarias) {
            if (orden.getEstado() == EstadoOrdenCompra.PENDIENTE) {
                // Calcular cantidad a ordenar (por ejemplo, 2 veces el stock mínimo)
                int cantidadOrdenar = orden.getMateriaPrima().getStockMinimo() * 2;
                
                OrdenCompraModel nuevaOrden = new OrdenCompraModel();
                nuevaOrden.setMateriaPrima(orden.getMateriaPrima());
                nuevaOrden.setProveedor(orden.getProveedor());
                nuevaOrden.setCantidad(cantidadOrdenar);
                nuevaOrden.setPrecioUnitario(orden.getPrecioUnitario());
                nuevaOrden.setEstado(EstadoOrdenCompra.PENDIENTE);
                nuevaOrden.setFechaEstimadaEntrega(LocalDateTime.now().plusDays(7)); // Estimación de 7 días
                
                ordenCompraRepository.save(nuevaOrden);
            }
        }
    }
} 