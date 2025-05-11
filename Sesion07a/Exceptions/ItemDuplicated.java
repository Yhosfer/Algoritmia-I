package Sesion07a.Exceptions;

public class ItemDuplicated extends RuntimeException {
    public ItemDuplicated(String message) {
        super(message);
    }
}
