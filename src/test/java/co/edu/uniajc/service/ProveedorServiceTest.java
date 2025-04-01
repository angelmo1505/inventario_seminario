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
    private ProveedorRepository repository;

    @InjectMocks
    private ProveedorService service;

    private ProveedorModel proveedor;

    @BeforeEach
    void setUp() {
        proveedor = new ProveedorModel();
        proveedor.setId(1L);
    }

    @Test
    void testFindAll() {
        List<ProveedorModel> proveedores = Arrays.asList(proveedor);
        when(repository.findAll()).thenReturn(proveedores);

        List<ProveedorModel> result = service.findAll();
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(repository.findById(1L)).thenReturn(Optional.of(proveedor));

        Optional<ProveedorModel> result = service.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testCreate() {
        when(repository.save(proveedor)).thenReturn(proveedor);

        ProveedorModel result = service.create(proveedor);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).save(proveedor);
    }

    @Test
    void testUpdate() {
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(proveedor)).thenReturn(proveedor);

        ProveedorModel result = service.update(proveedor);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).existsById(1L);
        verify(repository, times(1)).save(proveedor);
    }

    @Test
    void testUpdateThrowsExceptionWhenNotFound() {
        when(repository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.update(proveedor));
        assertEquals("Proveedor no encontrado", exception.getMessage());
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
        assertEquals("Proveedor no encontrado", exception.getMessage());
    }
}
