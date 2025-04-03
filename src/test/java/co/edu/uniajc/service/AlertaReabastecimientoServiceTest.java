package co.edu.uniajc.service;

import co.edu.uniajc.model.AlertaReabastecimientoModel;
import co.edu.uniajc.repository.AlertaReabastecimientoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlertaReabastecimientoServiceTest {

    @Mock
    private AlertaReabastecimientoRepository repository;

    @InjectMocks
    private AlertaReabastecimientoService service;

    @Test
    void testFindWithFilters() {

        String fecha = "2024-04-01";
        String categoria = "Electronicos";
        String estado = "Pendiente";
        String usuario = "usuario1";
        String criticidad = "Alta";
        int page = 0;
        int size = 10;

        AlertaReabastecimientoModel alerta = new AlertaReabastecimientoModel();
        alerta.setId(1L);
        alerta.setFecha(LocalDateTime.parse(fecha + "T00:00:00")); // Convertir a LocalDateTime si es necesario
        alerta.setCategoria(categoria);
        alerta.setEstado(estado);
        alerta.setUsuario(usuario);
        alerta.setCriticidad(criticidad);

        Page<AlertaReabastecimientoModel> expectedPage = new PageImpl<>(List.of(alerta));

        when(repository.findByFilters(eq(fecha), eq(categoria), eq(estado), eq(usuario), eq(criticidad), any(Pageable.class)))
                .thenReturn(expectedPage);

        Page<AlertaReabastecimientoModel> result = service.findWithFilters(fecha, categoria, estado, usuario, criticidad, page, size);

        assertNotNull(result, "El resultado no debe ser null");
        assertFalse(result.isEmpty(), "El resultado no debe estar vacío");
        assertEquals(1, result.getTotalElements(), "Debe haber exactamente un elemento en la página");

        verify(repository, times(1)).findByFilters(eq(fecha), eq(categoria), eq(estado), eq(usuario), eq(criticidad), any(Pageable.class));
    }

    @Test
    void testCreate() {
        AlertaReabastecimientoModel alerta = new AlertaReabastecimientoModel();
        alerta.setId(1L);
        alerta.setFecha(LocalDateTime.now());
        alerta.setCategoria("Categoria Test");
        alerta.setEstado("Pendiente");
        alerta.setUsuario("Usuario Test");
        alerta.setCriticidad("Alta");

        when(repository.save(any(AlertaReabastecimientoModel.class))).thenReturn(alerta);

        AlertaReabastecimientoModel result = service.create(alerta);

        assertNotNull(result, "El resultado de create() no debería ser null");
        assertEquals(alerta.getId(), result.getId(), "El ID debe coincidir");
        assertEquals(alerta.getFecha(), result.getFecha(), "La fecha debe coincidir");
        assertEquals(alerta.getCategoria(), result.getCategoria(), "La categoría debe coincidir");
        assertEquals(alerta.getEstado(), result.getEstado(), "El estado debe coincidir");
        assertEquals(alerta.getUsuario(), result.getUsuario(), "El usuario debe coincidir");
        assertEquals(alerta.getCriticidad(), result.getCriticidad(), "La criticidad debe coincidir");

        verify(repository, times(1)).save(any(AlertaReabastecimientoModel.class));
    }
}