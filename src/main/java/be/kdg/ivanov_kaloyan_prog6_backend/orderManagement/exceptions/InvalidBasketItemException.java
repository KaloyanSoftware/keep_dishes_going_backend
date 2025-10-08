package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions;

public class InvalidBasketItemException extends RuntimeException {
    public InvalidBasketItemException(String message) {
        super(message);
    }
}
