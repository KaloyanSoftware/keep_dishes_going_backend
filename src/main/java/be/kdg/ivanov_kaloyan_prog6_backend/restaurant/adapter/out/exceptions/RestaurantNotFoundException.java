package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.exceptions;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(String message) {
        super(message);
    }
}
