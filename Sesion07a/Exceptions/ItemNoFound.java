package Sesion07a.Exceptions;

public class ItemNoFound extends RuntimeException {
    public ItemNoFound(String message) {
        super(message);
    }
}
