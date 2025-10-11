package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions;

public class DraftNotFoundException extends RuntimeException {
    public DraftNotFoundException(String message) {
        super(message);
    }
}
