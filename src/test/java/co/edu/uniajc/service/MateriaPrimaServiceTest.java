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
    private MateriaPrimaRepository materiaPrimaRepository;

    @InjectMocks
    private MateriaPrimaService materiaPrimaService;

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
        when(materiaPrimaRepository.findAll()).thenReturn(materias);

        List<MateriaPrimaModel> result = materiaPrimaService.findAll();
        assertEquals(1, result.size());
        verify(materiaPrimaRepository,  times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(materiaPrimaRepository.findById(1L)).thenReturn(Optional.of(materiaPrima));

        Optional<MateriaPrimaModel> result = materiaPrimaService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(materiaPrimaRepository,  times(1)).findById(1L);
    }

    @Test
    void testCreate() {
        when(materiaPrimaRepository.save(materiaPrima)).thenReturn(materiaPrima);

        MateriaPrimaModel result = materiaPrimaService.create(materiaPrima);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(materiaPrimaRepository,  times(1)).save(materiaPrima);
    }

    @Test
    void testUpdate() {
        when(materiaPrimaRepository.existsById(1L)).thenReturn(true);
        when(materiaPrimaRepository.save(materiaPrima)).thenReturn(materiaPrima);

        MateriaPrimaModel result = materiaPrimaService.update(materiaPrima);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(materiaPrimaRepository,  times(1)).existsById(1L);
        verify(materiaPrimaRepository,  times(1)).save(materiaPrima);
    }

    @Test
    void testUpdateThrowsExceptionWhenNotFound() {
        when(materiaPrimaRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> materiaPrimaService.update(materiaPrima));
        assertEquals("Materia Prima no encontrada", exception.getMessage());
    }

    @Test
    void testDelete() {
        when(materiaPrimaRepository.existsById(1L)).thenReturn(true);
        doNothing().when(materiaPrimaRepository).deleteById(1L);

        materiaPrimaService.delete(1L);
        verify(materiaPrimaRepository,  times(1)).existsById(1L);
        verify(materiaPrimaRepository,  times(1)).deleteById(1L);
    }

    @Test
    void testDeleteThrowsExceptionWhenNotFound() {
        when(materiaPrimaRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> materiaPrimaService.delete(1L));
        assertEquals("Materia Prima no encontrada", exception.getMessage());
    }
}
