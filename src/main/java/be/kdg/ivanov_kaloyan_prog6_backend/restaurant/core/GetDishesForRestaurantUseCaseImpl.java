package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Dish;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Menu;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions.MenuNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.GetDishesForRestaurantCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.GetDishesForRestaurantUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadMenuPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetDishesForRestaurantUseCaseImpl implements GetDishesForRestaurantUseCase {

    private final LoadMenuPort port;

    public GetDishesForRestaurantUseCaseImpl(LoadMenuPort port) {
        this.port = port;
    }

    @Override
    public List<Dish> getDishes(GetDishesForRestaurantCommand command) {
        final Menu menu = port.loadByRestaurantId(command.restaurantId()).orElseThrow(
                () -> new MenuNotFoundException("Menu that belongs to a restaurant with id: " + command.restaurantId() + " doesn't exist!")
        );

        return menu.getDishes().values().stream().toList();
    }
}
