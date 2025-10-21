package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions.MenuNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Menu;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.MarkDishBackInStockCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.MarkDishBackInStockUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadMenuPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.UpdateMenuPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkDishBackInStockUseCaseImplementation implements MarkDishBackInStockUseCase {

    private final LoadMenuPort loadMenuPort;

    private final List<UpdateMenuPort> updateMenuPorts;

    public MarkDishBackInStockUseCaseImplementation(final LoadMenuPort loadMenuPort,
                                                    final List<UpdateMenuPort> updateMenuPorts){
        this.loadMenuPort = loadMenuPort;
        this.updateMenuPorts = updateMenuPorts;
    }

    @Override
    @Transactional
    public void markBackInStock(MarkDishBackInStockCommand command) {
        Menu menu = loadMenuPort.loadById(command.menuId()).orElseThrow(() -> new MenuNotFoundException("Menu not found"));

        menu.markDishBackInStock(command.dishId());

        this.updateMenuPorts.forEach(port -> port.update(menu));
    }
}
