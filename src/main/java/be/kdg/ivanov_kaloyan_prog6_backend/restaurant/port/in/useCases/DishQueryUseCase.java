package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Dish;
import java.util.UUID;

public interface DishQueryUseCase {
    Dish findPublishedDish(UUID restaurantId, UUID dishId);

}
