package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in;

import java.util.UUID;

public interface MarkDishOutOfStockUseCase {

    void markOutOfStock(UUID restaurantId, UUID dishId);
}
