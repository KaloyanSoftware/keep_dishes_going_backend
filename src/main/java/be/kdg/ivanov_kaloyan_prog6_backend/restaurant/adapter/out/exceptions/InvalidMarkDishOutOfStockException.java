package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.exceptions;

public class InvalidMarkDishOutOfStockException extends RuntimeException {
    public InvalidMarkDishOutOfStockException(String message) {
        super(message);
    }
}
