package co.edu.uniajc.service;

import co.edu.uniajc.model.AlertaReabastecimientoModel;
import co.edu.uniajc.repository.AlertaReabastecimientoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlertaReabastecimientoServiceTest {

    @Mock
    private AlertaReabastecimientoRepository alertaReabastecimientoRepository;

    @InjectMocks
    private AlertaReabastecimientoService alertaReabastecimientoService;

    private AlertaReabastecimientoModel alerta;

    @BeforeEach
    void setUp() {
        alerta = new AlertaReabastecimientoModel();
        alerta.setId(1L);
        alerta.setDescripcion("Alerta de reabastecimiento");
    }

    @Test
    void testFindAll() {
        List<AlertaReabastecimientoModel> alertas = Arrays.asList(alerta);
        when(alertaReabastecimientoRepository.findAll()).thenReturn(alertas);

        List<AlertaReabastecimientoModel> result = alertaReabastecimientoService.findAll();
        assertEquals(1, result.size());
        verify(alertaReabastecimientoRepository, times(1)).findAll();
    }

    @Test
    void testCreate() {
        when(alertaReabastecimientoRepository.save(alerta)).thenReturn(alerta);

        AlertaReabastecimientoModel result = alertaReabastecimientoService.create(alerta);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Alerta de reabastecimiento", result.getDescripcion());
        verify(alertaReabastecimientoRepository, times(1)).save(alerta);
    }
}
