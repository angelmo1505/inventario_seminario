import co.edu.uniajc.model.AlertaReabastecimientoModel;
import co.edu.uniajc.repository.AlertaReabastecimientoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlertaReabastecimientoServiceTest {

    @Mock
    private AlertaReabastecimientoRepository repository;

    @InjectMocks
    private AlertaReabastecimientoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindWithFilters() {
        String fecha = "2024-04-01";
        String categoria = "Electronicos";
        String estado = "Pendiente";
        String usuario = "usuario1";
        String criticidad = "Alta";
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size, Sort.by("fecha").descending());
        Page<AlertaReabastecimientoModel> expectedPage = new PageImpl<>(Collections.emptyList());

        when(repository.findByFilters(fecha, categoria, estado, usuario, criticidad, pageable)).thenReturn(expectedPage);

        Page<AlertaReabastecimientoModel> result = service.findWithFilters(fecha, categoria, estado, usuario, criticidad, page, size);

        assertNotNull(result);
        assertEquals(0, result.getTotalElements());
        verify(repository, times(1)).findByFilters(fecha, categoria, estado, usuario, criticidad, pageable);
    }

    @Test
    void testCreate() {
        AlertaReabastecimientoModel alerta = new AlertaReabastecimientoModel();
        when(repository.save(alerta)).thenReturn(alerta);

        AlertaReabastecimientoModel result = service.create(alerta);

        assertNotNull(result);
        verify(repository, times(1)).save(alerta);
    }
}