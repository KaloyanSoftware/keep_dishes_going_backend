package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Restaurant;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.MarkDishOutOfStockUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadRestaurantPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.SaveRestaurantPort;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class MarkDishOutOfStockUseCaseImpl implements MarkDishOutOfStockUseCase {

    private final LoadRestaurantPort loadRestaurantPort;

    private final SaveRestaurantPort saveRestaurantPort;

    public MarkDishOutOfStockUseCaseImpl(final LoadRestaurantPort loadRestaurantPort,
                                         final SaveRestaurantPort saveRestaurantPort) {
        this.loadRestaurantPort = loadRestaurantPort;
        this.saveRestaurantPort = saveRestaurantPort;
    }

    @Override
    public void markOutOfStock(UUID restaurantId,  UUID dishId) {
        Restaurant restaurant = loadRestaurantPort.loadBy(restaurantId).orElseThrow();
        restaurant.markDishOutOfStock(dishId);
        saveRestaurantPort.save(restaurant);
    }

}
