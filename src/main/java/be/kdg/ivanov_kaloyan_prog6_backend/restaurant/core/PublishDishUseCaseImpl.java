package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions.MenuNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Dish;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Menu;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.PublishDishCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.PublishDishUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadMenuPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.UpdateMenuPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class PublishDishUseCaseImpl implements PublishDishUseCase {

    private final LoadMenuPort loadMenuPort;

    private final List<UpdateMenuPort> updateMenuPorts;

    public PublishDishUseCaseImpl(final LoadMenuPort loadMenuPort,
                                  final List<UpdateMenuPort> updateMenuPorts) {
        this.loadMenuPort = loadMenuPort;
        this.updateMenuPorts = updateMenuPorts;
    }

    @Override
    public Dish publish(PublishDishCommand command) {
        Menu menu = loadMenuPort.loadByRestaurantId(command.restaurantId()).orElseThrow(
                () -> new MenuNotFoundException("Menu with restaurant id: " + command.restaurantId() + " not found!"));

        Dish dish = menu.publishDish(command.dishId());

        this.updateMenuPorts.forEach(port -> port.update(menu));
        menu.commitEvents();

        return dish;
    }
}
