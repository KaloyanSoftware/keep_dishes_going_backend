package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions;

public class InvalidMarkDishBackInStockException extends RuntimeException {
    public InvalidMarkDishBackInStockException(String message) {
        super(message);
    }
}
