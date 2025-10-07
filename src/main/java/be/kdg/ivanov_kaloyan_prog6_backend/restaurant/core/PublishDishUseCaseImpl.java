package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.exceptions.MenuNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Dish;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Menu;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.PublishDishCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.PublishDishUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadMenuPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.SaveMenuPort;
import org.springframework.stereotype.Service;

@Service
public class PublishDishUseCaseImpl implements PublishDishUseCase {

    private final LoadMenuPort loadMenuPort;

    private final SaveMenuPort saveMenuPort;

    public PublishDishUseCaseImpl(final LoadMenuPort loadMenuPort,
                                  final SaveMenuPort saveMenuPort) {
        this.loadMenuPort = loadMenuPort;
        this.saveMenuPort = saveMenuPort;
    }

    @Override
    public Dish publish(PublishDishCommand command) {
        Menu menu = loadMenuPort.loadById(command.menuId()).orElseThrow(() -> new MenuNotFoundException("Menu not found"));

        Dish dish = menu.publishDish(command.dishId());

        saveMenuPort.save(menu);

        return dish;
    }
}
