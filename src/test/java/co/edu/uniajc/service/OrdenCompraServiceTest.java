package co.edu.uniajc.service;

import co.edu.uniajc.model.OrdenCompraModel;
import co.edu.uniajc.repository.OrdenCompraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrdenCompraServiceTest {

    @Mock
    private OrdenCompraRepository repository;

    @InjectMocks
    private OrdenCompraService service;

    private OrdenCompraModel orden;

    @BeforeEach
    void setUp() {
        orden = new OrdenCompraModel();
        orden.setId(1L);
        orden.setFecha(LocalDate.of(2024, 04, 01));
        orden.setTotal(new BigDecimal("1000.00"));
        orden.setEstado("Pendiente");
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(orden));
        List<OrdenCompraModel> result = service.findAll();
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindById_Found() {
        when(repository.findById(1L)).thenReturn(Optional.of(orden));
        Optional<OrdenCompraModel> result = service.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(orden, result.get());
    }

    @Test
    void testFindById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        Optional<OrdenCompraModel> result = service.findById(1L);
        assertFalse(result.isPresent());
    }

    @Test
    void testCreate_Success() {
        when(repository.save(any(OrdenCompraModel.class))).thenReturn(orden);
        OrdenCompraModel result = service.create(orden);
        assertNotNull(result);
        assertEquals(orden, result);
    }

    @Test
    void testCreate_Failure() {
        orden.setProveedor(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.create(orden));
        assertEquals("Proveedor, fecha y total son obligatorios.", exception.getMessage());
    }

    @Test
    void testUpdate_Success() {
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(any(OrdenCompraModel.class))).thenReturn(orden);
        OrdenCompraModel result = service.update(orden);
        assertEquals(orden, result);
    }

    @Test
    void testUpdate_NotFound() {
        when(repository.existsById(1L)).thenReturn(false);
        Exception exception = assertThrows(RuntimeException.class, () -> service.update(orden));
        assertEquals("Orden de compra no encontrada", exception.getMessage());
    }

    @Test
    void testDelete_Success() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);
        service.delete(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testDelete_NotFound() {
        when(repository.existsById(1L)).thenReturn(false);
        Exception exception = assertThrows(RuntimeException.class, () -> service.delete(1L));
        assertEquals("Orden de compra no encontrada", exception.getMessage());
    }

    @Test
    void testActualizarEstadoOrden() {
        when(repository.findById(1L)).thenReturn(Optional.of(orden));
        when(repository.save(any(OrdenCompraModel.class))).thenReturn(orden);
        OrdenCompraModel result = service.actualizarEstadoOrden(1L, "Aprobado");
        assertEquals("Aprobado", result.getEstado());
    }

    @Test
    void testActualizarEstadoOrden_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class, () -> service.actualizarEstadoOrden(1L, "Aprobado"));
        assertEquals("Orden no encontrada", exception.getMessage());
    }

    @Test
    void testObtenerReporteOrdenesCompra() {
        when(repository.findAll()).thenReturn(Arrays.asList(orden));
        List<OrdenCompraModel> result = service.obtenerReporteOrdenesCompra();
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }
}