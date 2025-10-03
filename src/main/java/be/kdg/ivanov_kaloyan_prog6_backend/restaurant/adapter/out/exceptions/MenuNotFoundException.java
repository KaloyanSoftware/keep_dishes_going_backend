package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.exceptions;

public class MenuNotFoundException extends RuntimeException {

    public MenuNotFoundException(String message) {
        super(message);
    }
}
