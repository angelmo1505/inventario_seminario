package co.edu.uniajc.service;

import co.edu.uniajc.model.DistribucionModel;
import co.edu.uniajc.repository.DistribucionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DistribucionServiceTest {

    @Mock
    private DistribucionRepository repository;

    @InjectMocks
    private DistribucionService service;

    private DistribucionModel distribucion;

    @BeforeEach
    void setUp() {
        distribucion = new DistribucionModel();
        distribucion.setId(1L);
    }

    @Test
    void testFindAll() {
        List<DistribucionModel> distribuciones = Arrays.asList(distribucion);
        when(repository.findAll()).thenReturn(distribuciones);

        List<DistribucionModel> result = service.findAll();
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(repository.findById(1L)).thenReturn(Optional.of(distribucion));

        Optional<DistribucionModel> result = service.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testCreate() {
        when(repository.save(distribucion)).thenReturn(distribucion);

        DistribucionModel result = service.create(distribucion);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).save(distribucion);
    }

    @Test
    void testUpdate() {
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(distribucion)).thenReturn(distribucion);

        DistribucionModel result = service.update(distribucion);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).existsById(1L);
        verify(repository, times(1)).save(distribucion);
    }

    @Test
    void testUpdateThrowsExceptionWhenNotFound() {
        when(repository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.update(distribucion));
        assertEquals("distribucion no encontrada", exception.getMessage());
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
        assertEquals("Distribución no encontrada", exception.getMessage());
    }
}
