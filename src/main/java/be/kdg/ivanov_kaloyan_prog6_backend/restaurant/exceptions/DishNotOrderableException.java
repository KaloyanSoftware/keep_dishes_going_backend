package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions;

public class DishNotOrderableException extends RuntimeException {
    public DishNotOrderableException(String message) {
        super(message);
    }
}
