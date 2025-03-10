package co.edu.uniajc.exception;

public class ReporteNotFoundException extends RuntimeException {
    public ReporteNotFoundException(Long id) {
        super("Reporte no encontrado con ID: " + id);
    }
}