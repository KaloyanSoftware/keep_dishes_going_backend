package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(String message) {
        super(message);
    }
}
