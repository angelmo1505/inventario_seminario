package co.edu.uniajc.exception;

public class MateriaPrimaNotFoundException extends RuntimeException {
    public MateriaPrimaNotFoundException(Long id) {
        super("Materia Prima no encontrada con ID: " + id);
    }
}
