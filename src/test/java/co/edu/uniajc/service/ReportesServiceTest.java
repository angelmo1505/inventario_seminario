package co.edu.uniajc.service;

import co.edu.uniajc.model.ReportesModel;
import co.edu.uniajc.repository.ReportesRepository;
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
class ReportesServiceTest {

    @Mock
    private ReportesRepository repository;

    @InjectMocks
    private ReportesService service;

    private ReportesModel reporte;

    @BeforeEach
    void setUp() {
        reporte = new ReportesModel();
        reporte.setId(1L);
    }

    @Test
    void testFindAll() {
        List<ReportesModel> reportes = Arrays.asList(reporte);
        when(repository.findAll()).thenReturn(reportes);

        List<ReportesModel> result = service.findAll();
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(repository.findById(1L)).thenReturn(Optional.of(reporte));

        Optional<ReportesModel> result = service.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testCreate() {
        when(repository.save(reporte)).thenReturn(reporte);

        ReportesModel result = service.create(reporte);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).save(reporte);
    }

    @Test
    void testUpdate() {
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(reporte)).thenReturn(reporte);

        ReportesModel result = service.update(reporte);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).existsById(1L);
        verify(repository, times(1)).save(reporte);
    }

    @Test
    void testUpdateThrowsExceptionWhenNotFound() {
        when(repository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.update(reporte));
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
        assertEquals("Reporte no encontrado", exception.getMessage());
    }
}
