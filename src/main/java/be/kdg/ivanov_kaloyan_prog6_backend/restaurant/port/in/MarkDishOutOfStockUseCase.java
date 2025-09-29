package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface MarkDishOutOfStockUseCase {

    void markOutOfStock(UUID restaurantId, UUID dishId);
}
