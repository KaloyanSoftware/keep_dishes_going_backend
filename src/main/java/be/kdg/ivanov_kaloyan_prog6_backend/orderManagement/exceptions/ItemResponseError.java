package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions;

public class ItemResponseError extends RuntimeException {
    public ItemResponseError(String message) {
        super(message);
    }
}
