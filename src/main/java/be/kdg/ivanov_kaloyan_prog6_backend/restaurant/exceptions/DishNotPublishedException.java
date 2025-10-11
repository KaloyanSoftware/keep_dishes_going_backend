package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions;

public class DishNotPublishedException extends RuntimeException {
    public DishNotPublishedException(String message) {
        super(message);
    }
}
