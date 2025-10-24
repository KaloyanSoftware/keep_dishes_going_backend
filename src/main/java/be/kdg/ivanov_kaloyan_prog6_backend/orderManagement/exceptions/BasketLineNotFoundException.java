package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions;

public class BasketLineNotFoundException extends RuntimeException {
    public BasketLineNotFoundException(String message) {
        super(message);
    }
}
