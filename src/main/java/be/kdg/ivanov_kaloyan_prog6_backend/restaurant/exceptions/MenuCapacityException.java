package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions;

public class MenuCapacityException extends RuntimeException {
    public MenuCapacityException(String message) {
        super(message);
    }
}
