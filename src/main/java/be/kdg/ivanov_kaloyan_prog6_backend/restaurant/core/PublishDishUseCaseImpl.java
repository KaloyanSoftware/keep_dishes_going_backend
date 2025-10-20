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
public class PublishDishUseCaseImpl implements PublishDishUseCase {

    private final LoadMenuPort loadMenuPort;

    private final List<UpdateMenuPort> updateMenuPorts;

    public PublishDishUseCaseImpl(final LoadMenuPort loadMenuPort,
                                  final List<UpdateMenuPort> updateMenuPorts) {
        this.loadMenuPort = loadMenuPort;
        this.updateMenuPorts = updateMenuPorts;
    }

    @Override
    @Transactional
    public void publish(PublishDishCommand command) {
        Menu menu = loadMenuPort.loadById(command.menuId()).orElseThrow(() -> new MenuNotFoundException("Menu not found"));

        Dish dish = menu.publishDish(command.dishId());

        this.updateMenuPorts.forEach(port -> port.update(menu));
    }
}
