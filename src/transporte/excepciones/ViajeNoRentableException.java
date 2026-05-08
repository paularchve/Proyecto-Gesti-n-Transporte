package transporte.excepciones;

// Excepción personalizada para indicar que un viaje no es rentable
public class ViajeNoRentableException extends Exception {

    public ViajeNoRentableException() {
        super("El viaje no es rentable");
    }

    public ViajeNoRentableException(String mensaje) {
        super(mensaje);
    }
}