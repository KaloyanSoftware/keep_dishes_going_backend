package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Dish;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Menu;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions.DishNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions.MenuNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.DishQueryUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadMenuPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DishQueryUseCaseImpl implements DishQueryUseCase {
    private final LoadMenuPort loadMenuPort;

    public DishQueryUseCaseImpl(final LoadMenuPort loadMenuPort) {
        this.loadMenuPort = loadMenuPort;
    }

    @Override
    public Dish findPublishedDish(UUID restaurantId, UUID dishId) {
        final Menu menu = loadMenuPort.loadByRestaurantId(restaurantId).orElseThrow(
                () -> new MenuNotFoundException("Can't find menu with restaurantId: " + restaurantId)
        );

        return menu.findPublishedDish(dishId).orElseThrow(
                () -> new DishNotFoundException("Can't find dish with id: " + dishId));
    }
}
