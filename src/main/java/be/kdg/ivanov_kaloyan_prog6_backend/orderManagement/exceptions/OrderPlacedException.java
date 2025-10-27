package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions;

public class OrderPlacedException extends RuntimeException {
    public OrderPlacedException(String message) {
        super(message);
    }
}
