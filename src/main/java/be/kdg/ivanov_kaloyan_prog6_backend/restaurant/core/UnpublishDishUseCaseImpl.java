package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions.MenuNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Dish;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Menu;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.UnpublishDishCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.UnpublishDishUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadMenuPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.UpdateMenuPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UnpublishDishUseCaseImpl implements UnpublishDishUseCase {
    private final LoadMenuPort loadMenuPort;

    private final List<UpdateMenuPort> updateMenuPorts;

    public UnpublishDishUseCaseImpl(final LoadMenuPort loadMenuPort,
                                    final List<UpdateMenuPort> updateMenuPorts) {
        this.loadMenuPort = loadMenuPort;
        this.updateMenuPorts = updateMenuPorts;
    }

    @Override
    @Transactional
    public Dish unpublish(UnpublishDishCommand command) {
        Menu menu = loadMenuPort.loadById(command.menuId()).orElseThrow(
                () -> new MenuNotFoundException("Can't find menu with id: " + command.menuId()));

        Dish dish = menu.unpublishDish(command.dishId());

        this.updateMenuPorts.forEach(port -> port.update(menu));

        return dish;
    }
}
