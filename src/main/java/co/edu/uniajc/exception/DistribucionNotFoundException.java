package co.edu.uniajc.exception;

public class DistribucionNotFoundException extends RuntimeException {
    public DistribucionNotFoundException(Long id) {
        super("Distribución no encontrada con ID: " + id);
    }
}
