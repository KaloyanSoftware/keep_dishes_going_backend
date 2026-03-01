package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Menu;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions.RestaurantNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.DeleteDishCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.DeleteDishUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadMenuPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.UpdateMenuPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeleteDishUseCaseImpl implements DeleteDishUseCase {
    private final LoadMenuPort loadMenuPort;

    private final UpdateMenuPort updateMenuPort;

    public DeleteDishUseCaseImpl(final LoadMenuPort loadMenuPort,
                                 final @Qualifier("jpa") UpdateMenuPort updateMenuPort) {
        this.loadMenuPort = loadMenuPort;
        this.updateMenuPort = updateMenuPort;
    }


    @Override
    public void deleteDish(DeleteDishCommand command) {
        final Menu menu = loadMenuPort.loadByRestaurantId(command.restaurantId()).orElseThrow(
                () -> new RestaurantNotFoundException("Restaurant with id " + command.restaurantId() + " not found!")
        );

        menu.deleteDish(command.dishId());

        updateMenuPort.update(menu);
    }
}
