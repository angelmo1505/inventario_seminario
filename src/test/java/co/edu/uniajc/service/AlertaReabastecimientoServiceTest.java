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
    private AlertaReabastecimientoRepository repository;

    @InjectMocks
    private AlertaReabastecimientoService service;

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
        when(repository.findAll()).thenReturn(alertas);

        List<AlertaReabastecimientoModel> result = service.findAll();
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testCreate() {
        when(repository.save(alerta)).thenReturn(alerta);

        AlertaReabastecimientoModel result = service.create(alerta);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Alerta de reabastecimiento", result.getDescripcion());
        verify(repository, times(1)).save(alerta);
    }
}
