package transporte.excepciones;

// Excepción personalizada para indicar que un bus no fue encontrado
public class BusNoEncontradoException extends Exception {

    public BusNoEncontradoException() {
        super("El bus no fue encontrado en el sistema");
    }

    public BusNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}