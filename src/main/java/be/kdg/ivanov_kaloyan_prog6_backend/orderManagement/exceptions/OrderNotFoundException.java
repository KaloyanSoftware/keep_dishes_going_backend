package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
