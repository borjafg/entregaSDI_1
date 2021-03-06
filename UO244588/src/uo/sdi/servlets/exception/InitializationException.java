package uo.sdi.servlets.exception;

/**
 * Indica que ha habido algun problema durante la inicializacion del
 * controlador.
 * 
 */
public class InitializationException extends Exception {

    private static final long serialVersionUID = 4001710687990554589L;

    public InitializationException() {
    }

    public InitializationException(String message) {
	super(message);
    }

    public InitializationException(Throwable cause) {
	super(cause);
    }

    public InitializationException(String message, Throwable cause) {
	super(message, cause);
    }

}