package co.edu.uniajc.exception;

public class OrdenCompraNotFoundException extends RuntimeException {
    public OrdenCompraNotFoundException(Long id) {
        super("Orden de Compra no encontrada con ID: " + id);
    }
}