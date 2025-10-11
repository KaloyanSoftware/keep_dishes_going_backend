package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions;

public class MenuNotFoundException extends RuntimeException {

    public MenuNotFoundException(String message) {
        super(message);
    }
}
