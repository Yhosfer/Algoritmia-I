package Sesion08.Exceptions;

public class ItemNoFound extends RuntimeException {
    public ItemNoFound(String message) {
        super(message);
    }
}
