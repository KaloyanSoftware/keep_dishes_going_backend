package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions;

public class InvalidPublishDishException extends RuntimeException {
    public InvalidPublishDishException(String message) {
        super(message);
    }
}
