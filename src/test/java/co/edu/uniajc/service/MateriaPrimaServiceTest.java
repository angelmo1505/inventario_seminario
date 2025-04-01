package co.edu.uniajc.service;

import co.edu.uniajc.model.MateriaPrimaModel;
import co.edu.uniajc.repository.MateriaPrimaRepository;
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
class MateriaPrimaServiceTest {

    @Mock
    private MateriaPrimaRepository repository;

    @InjectMocks
    private MateriaPrimaService service;

    private MateriaPrimaModel materiaPrima;

    @BeforeEach
    void setUp() {
        materiaPrima = new MateriaPrimaModel();
        materiaPrima.setId(1L);
        materiaPrima.setNombre("Acero");
    }

    @Test
    void testFindAll() {
        List<MateriaPrimaModel> materias = Arrays.asList(materiaPrima);
        when(repository.findAll()).thenReturn(materias);

        List<MateriaPrimaModel> result = service.findAll();
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(repository.findById(1L)).thenReturn(Optional.of(materiaPrima));

        Optional<MateriaPrimaModel> result = service.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testCreate() {
        when(repository.save(materiaPrima)).thenReturn(materiaPrima);

        MateriaPrimaModel result = service.create(materiaPrima);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).save(materiaPrima);
    }

    @Test
    void testUpdate() {
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(materiaPrima)).thenReturn(materiaPrima);

        MateriaPrimaModel result = service.update(materiaPrima);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).existsById(1L);
        verify(repository, times(1)).save(materiaPrima);
    }

    @Test
    void testUpdateThrowsExceptionWhenNotFound() {
        when(repository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.update(materiaPrima));
        assertEquals("Materia Prima no encontrada", exception.getMessage());
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
        assertEquals("Materia Prima no encontrada", exception.getMessage());
    }
}
