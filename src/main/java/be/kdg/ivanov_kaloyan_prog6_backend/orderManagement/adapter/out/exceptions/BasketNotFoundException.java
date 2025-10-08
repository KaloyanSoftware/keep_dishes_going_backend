package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.exceptions;

public class BasketNotFoundException extends RuntimeException {
    public BasketNotFoundException(String message) {
        super(message);
    }
}
