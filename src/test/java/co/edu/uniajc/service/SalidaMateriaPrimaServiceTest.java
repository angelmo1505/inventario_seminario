package co.edu.uniajc.service;

import co.edu.uniajc.model.SalidaMateriaPrimaModel;
import co.edu.uniajc.repository.SalidaMateriaPrimaRepository;
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
class SalidaMateriaPrimaServiceTest {

    @Mock
    private SalidaMateriaPrimaRepository repository;

    @InjectMocks
    private SalidaMateriaPrimaService salidaMateriaPrimaService;

    private SalidaMateriaPrimaModel salidaMateriaPrimaModel;

    @BeforeEach
    void setUp() {
        salidaMateriaPrimaModel = new SalidaMateriaPrimaModel();
        salidaMateriaPrimaModel.setId(1L);
    }

    @Test
    void testFindAll() {
        List<SalidaMateriaPrimaModel> salidas = Arrays.asList(salidaMateriaPrimaModel);
        when(repository.findAll()).thenReturn(salidas);

        List<SalidaMateriaPrimaModel> result = salidaMateriaPrimaService.findAll();
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(repository.findById(1L)).thenReturn(Optional.of(salidaMateriaPrimaModel));

        Optional<SalidaMateriaPrimaModel> result = salidaMateriaPrimaService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testCreate() {
        when(repository.save(salidaMateriaPrimaModel)).thenReturn(salidaMateriaPrimaModel);

        SalidaMateriaPrimaModel result = salidaMateriaPrimaService.create(salidaMateriaPrimaModel);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).save(salidaMateriaPrimaModel);
    }

    @Test
    void testUpdate() {
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(salidaMateriaPrimaModel)).thenReturn(salidaMateriaPrimaModel);

        SalidaMateriaPrimaModel result = salidaMateriaPrimaService.update(salidaMateriaPrimaModel);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).existsById(1L);
        verify(repository, times(1)).save(salidaMateriaPrimaModel);
    }

    @Test
    void testUpdateThrowsExceptionWhenNotFound() {
        when(repository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> salidaMateriaPrimaService.update(salidaMateriaPrimaModel));
        assertEquals("Salida no encontrada", exception.getMessage());
    }

    @Test
    void testDelete() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        salidaMateriaPrimaService.delete(1L);
        verify(repository, times(1)).existsById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteThrowsExceptionWhenNotFound() {
        when(repository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> salidaMateriaPrimaService.delete(1L));
        assertEquals("Salida no encontrada", exception.getMessage());
    }
}
