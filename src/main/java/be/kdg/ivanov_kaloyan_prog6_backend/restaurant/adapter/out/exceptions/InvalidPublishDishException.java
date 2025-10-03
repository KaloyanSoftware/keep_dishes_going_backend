package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.exceptions;

public class InvalidPublishDishException extends RuntimeException {
    public InvalidPublishDishException(String message) {
        super(message);
    }
}
