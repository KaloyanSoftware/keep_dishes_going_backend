package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.exceptions;

public class InvalidMarkDishBackInStockException extends RuntimeException {
    public InvalidMarkDishBackInStockException(String message) {
        super(message);
    }
}
