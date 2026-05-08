package transporte.excepciones;

public class ViajeNoEncontradoException extends TransporteException {

    public ViajeNoEncontradoException() {
        super("Viaje no encontrado");
    }

    public ViajeNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}