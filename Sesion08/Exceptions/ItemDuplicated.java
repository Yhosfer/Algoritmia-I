package Sesion08.Exceptions;

public class ItemDuplicated extends RuntimeException {
    public ItemDuplicated(String message) {
        super(message);
    }
}
