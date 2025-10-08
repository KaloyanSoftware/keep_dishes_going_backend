package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions;

public class InvalidUnpublishDishException extends RuntimeException {
    public InvalidUnpublishDishException(String message) {
        super(message);
    }
}
