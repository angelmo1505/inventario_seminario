package co.edu.uniajc.exception;

public class SalidaMateriaPrimaNotFoundException extends RuntimeException {
    public SalidaMateriaPrimaNotFoundException(Long id) {
        super("Salida de Materia Prima no encontrada con ID: " + id);
    }
}
