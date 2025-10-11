package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
