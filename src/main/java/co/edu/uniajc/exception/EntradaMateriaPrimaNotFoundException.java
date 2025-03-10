package co.edu.uniajc.exception;

public class EntradaMateriaPrimaNotFoundException extends RuntimeException {
    public EntradaMateriaPrimaNotFoundException(Long id) {
        super("Entrada de Materia Prima no encontrada con ID: " + id);
    }
}
