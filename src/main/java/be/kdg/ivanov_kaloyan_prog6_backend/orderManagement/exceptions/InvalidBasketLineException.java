package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions;

public class InvalidBasketLineException extends RuntimeException {
    public InvalidBasketLineException(String message) {
        super(message);
    }
}
