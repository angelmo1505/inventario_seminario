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
    private OrdenCompraRepository ordenCompraRepository;

    @InjectMocks
    private OrdenCompraService ordenCompraService;

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
        when(ordenCompraRepository.findAll()).thenReturn(ordenes);

        List<OrdenCompraModel> result = ordenCompraService.findAll();
        assertEquals(1, result.size());
        verify(ordenCompraRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(ordenCompraRepository.findById(1L)).thenReturn(Optional.of(ordenCompra));

        Optional<OrdenCompraModel> result = ordenCompraService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(ordenCompraRepository, times(1)).findById(1L);
    }

    @Test
    void testCreate() {
        when(ordenCompraRepository.save(ordenCompra)).thenReturn(ordenCompra);

        OrdenCompraModel result = ordenCompraService.create(ordenCompra);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(ordenCompraRepository, times(1)).save(ordenCompra);
    }

    @Test
    void testUpdate() {
        when(ordenCompraRepository.existsById(1L)).thenReturn(true);
        when(ordenCompraRepository.save(ordenCompra)).thenReturn(ordenCompra);

        OrdenCompraModel result = ordenCompraService.update(ordenCompra);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(ordenCompraRepository, times(1)).existsById(1L);
        verify(ordenCompraRepository, times(1)).save(ordenCompra);
    }

    @Test
    void testUpdateThrowsExceptionWhenNotFound() {
        when(ordenCompraRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> ordenCompraService.update(ordenCompra));
        assertEquals("Orden de compra no encontrada", exception.getMessage());
    }

    @Test
    void testDelete() {
        when(ordenCompraRepository.existsById(1L)).thenReturn(true);
        doNothing().when(ordenCompraRepository).deleteById(1L);

        ordenCompraService.delete(1L);
        verify(ordenCompraRepository, times(1)).existsById(1L);
        verify(ordenCompraRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteThrowsExceptionWhenNotFound() {
        when(ordenCompraRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> ordenCompraService.delete(1L));
        assertEquals("Orden de compra no encontrada", exception.getMessage());
    }

    @Test
    void testActualizarEstadoOrden() {
        when(ordenCompraRepository.findById(1L)).thenReturn(Optional.of(ordenCompra));
        when(ordenCompraRepository.save(any(OrdenCompraModel.class))).thenReturn(ordenCompra);

        OrdenCompraModel result = ordenCompraService.actualizarEstadoOrden(1L, "Aprobada");
        assertNotNull(result);
        assertEquals("Aprobada", result.getEstado());
        verify(ordenCompraRepository, times(1)).findById(1L);
        verify(ordenCompraRepository, times(1)).save(ordenCompra);
    }

    @Test
    void testActualizarEstadoOrdenThrowsExceptionWhenNotFound() {
        when(ordenCompraRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> ordenCompraService.actualizarEstadoOrden(1L, "Aprobada"));
        assertEquals("Orden no encontrada", exception.getMessage());
    }

    @Test
    void testObtenerReporteOrdenesCompra() {
        List<OrdenCompraModel> ordenes = Arrays.asList(ordenCompra);
        when(ordenCompraRepository.findAll()).thenReturn(ordenes);

        List<OrdenCompraModel> result = ordenCompraService.obtenerReporteOrdenesCompra();
        assertEquals(1, result.size());
        verify(ordenCompraRepository, times(1)).findAll();
    }
}
