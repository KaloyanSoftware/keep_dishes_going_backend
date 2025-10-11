package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions;

public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException(String message) {
        super(message);
    }
}
