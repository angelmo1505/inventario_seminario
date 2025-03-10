package co.edu.uniajc.exception;

public class ProveedorNotFoundException extends RuntimeException {
    public ProveedorNotFoundException(Long id) {
        super("Proveedor no encontrado con ID: " + id);
    }
}
