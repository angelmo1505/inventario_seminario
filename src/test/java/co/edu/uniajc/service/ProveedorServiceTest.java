package co.edu.uniajc.service;

import co.edu.uniajc.model.ProveedorModel;
import co.edu.uniajc.repository.ProveedorRepository;
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
class ProveedorServiceTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorService proveedorService;

    private ProveedorModel proveedor;

    @BeforeEach
    void setUp() {
        proveedor = new ProveedorModel();
        proveedor.setId(1L);
    }

    @Test
    void testFindAll() {
        List<ProveedorModel> proveedores = Arrays.asList(proveedor);
        when(proveedorRepository.findAll()).thenReturn(proveedores);

        List<ProveedorModel> result = proveedorService.findAll();
        assertEquals(1, result.size());
        verify(proveedorRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(proveedorRepository.findById(1L)).thenReturn(Optional.of(proveedor));

        Optional<ProveedorModel> result = proveedorService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(proveedorRepository, times(1)).findById(1L);
    }

    @Test
    void testCreate() {
        when(proveedorRepository.save(proveedor)).thenReturn(proveedor);

        ProveedorModel result = proveedorService.create(proveedor);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(proveedorRepository, times(1)).save(proveedor);
    }

    @Test
    void testUpdate() {
        when(proveedorRepository.existsById(1L)).thenReturn(true);
        when(proveedorRepository.save(proveedor)).thenReturn(proveedor);

        ProveedorModel result = proveedorService.update(proveedor);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(proveedorRepository, times(1)).existsById(1L);
        verify(proveedorRepository, times(1)).save(proveedor);
    }

    @Test
    void testUpdateThrowsExceptionWhenNotFound() {
        when(proveedorRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> proveedorService.update(proveedor));
        assertEquals("Proveedor no encontrado", exception.getMessage());
    }

    @Test
    void testDelete() {
        when(proveedorRepository.existsById(1L)).thenReturn(true);
        doNothing().when(proveedorRepository).deleteById(1L);

        proveedorService.delete(1L);
        verify(proveedorRepository, times(1)).existsById(1L);
        verify(proveedorRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteThrowsExceptionWhenNotFound() {
        when(proveedorRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> proveedorService.delete(1L));
        assertEquals("Proveedor no encontrado", exception.getMessage());
    }
}
