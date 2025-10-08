package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions;

public class InvalidPublishDraftException extends RuntimeException {
    public InvalidPublishDraftException(String message) {
        super(message);
    }
}
