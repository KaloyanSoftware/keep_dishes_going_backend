package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions;

public class OrderProjectionNotFoundException extends RuntimeException {
    public OrderProjectionNotFoundException(String message) {
        super(message);
    }
}
