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
    private ReportesRepository reportesRepository;

    @InjectMocks
    private ReportesService reportesService;

    private ReportesModel reporte;

    @BeforeEach
    void setUp() {
        reporte = new ReportesModel();
        reporte.setId(1L);
    }

    @Test
    void testFindAll() {
        List<ReportesModel> reportes = Arrays.asList(reporte);
        when(reportesRepository.findAll()).thenReturn(reportes);

        List<ReportesModel> result = reportesService.findAll();
        assertEquals(1, result.size());
        verify(reportesRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(reportesRepository.findById(1L)).thenReturn(Optional.of(reporte));

        Optional<ReportesModel> result = reportesService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(reportesRepository, times(1)).findById(1L);
    }

    @Test
    void testCreate() {
        when(reportesRepository.save(reporte)).thenReturn(reporte);

        ReportesModel result = reportesService.create(reporte);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(reportesRepository, times(1)).save(reporte);
    }

    @Test
    void testUpdate() {
        when(reportesRepository.existsById(1L)).thenReturn(true);
        when(reportesRepository.save(reporte)).thenReturn(reporte);

        ReportesModel result = reportesService.update(reporte);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(reportesRepository, times(1)).existsById(1L);
        verify(reportesRepository, times(1)).save(reporte);
    }

    @Test
    void testUpdateThrowsExceptionWhenNotFound() {
        when(reportesRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> reportesService.update(reporte));
        assertEquals("distribucion no encontrada", exception.getMessage());
    }

    @Test
    void testDelete() {
        when(reportesRepository.existsById(1L)).thenReturn(true);
        doNothing().when(reportesRepository).deleteById(1L);

        reportesService.delete(1L);
        verify(reportesRepository, times(1)).existsById(1L);
        verify(reportesRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteThrowsExceptionWhenNotFound() {
        when(reportesRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> reportesService.delete(1L));
        assertEquals("Reporte no encontrado", exception.getMessage());
    }
}
