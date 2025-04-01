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

    private OrdenCompraModel ordenCompra;

    @BeforeEach
    void setUp() {
        ordenCompra = new OrdenCompraModel();
        ordenCompra.setId(1L);
        ordenCompra.setEstado("Pendiente");
    }

    @Test
    void testFindAll() {
        List<OrdenCompraModel> ordenes = Arrays.asList(ordenCompra);
        when(repository.findAll()).thenReturn(ordenes);

        List<OrdenCompraModel> result = service.findAll();
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(repository.findById(1L)).thenReturn(Optional.of(ordenCompra));

        Optional<OrdenCompraModel> result = service.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testCreate() {
        when(repository.save(ordenCompra)).thenReturn(ordenCompra);

        OrdenCompraModel result = service.create(ordenCompra);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).save(ordenCompra);
    }

    @Test
    void testUpdate() {
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(ordenCompra)).thenReturn(ordenCompra);

        OrdenCompraModel result = service.update(ordenCompra);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).existsById(1L);
        verify(repository, times(1)).save(ordenCompra);
    }

    @Test
    void testUpdateThrowsExceptionWhenNotFound() {
        when(repository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.update(ordenCompra));
        assertEquals("Orden de compra no encontrada", exception.getMessage());
    }

    @Test
    void testDelete() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        service.delete(1L);
        verify(repository, times(1)).existsById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteThrowsExceptionWhenNotFound() {
        when(repository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.delete(1L));
        assertEquals("Orden de compra no encontrada", exception.getMessage());
    }

    @Test
    void testActualizarEstadoOrden() {
        when(repository.findById(1L)).thenReturn(Optional.of(ordenCompra));
        when(repository.save(any(OrdenCompraModel.class))).thenReturn(ordenCompra);

        OrdenCompraModel result = service.actualizarEstadoOrden(1L, "Aprobada");
        assertNotNull(result);
        assertEquals("Aprobada", result.getEstado());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(ordenCompra);
    }

    @Test
    void testActualizarEstadoOrdenThrowsExceptionWhenNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.actualizarEstadoOrden(1L, "Aprobada"));
        assertEquals("Orden no encontrada", exception.getMessage());
    }

    @Test
    void testObtenerReporteOrdenesCompra() {
        List<OrdenCompraModel> ordenes = Arrays.asList(ordenCompra);
        when(repository.findAll()).thenReturn(ordenes);

        List<OrdenCompraModel> result = service.obtenerReporteOrdenesCompra();
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }
}
