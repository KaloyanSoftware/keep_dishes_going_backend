package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.core;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.DishProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.DishSavedProjector;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.DishSavedProjectionCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.UpdateDishProjectionPort;
import org.springframework.stereotype.Service;

@Service
public class DishSavedProjectionImpl implements DishSavedProjector {

    private final UpdateDishProjectionPort updateDishProjectionPort;

    public DishSavedProjectionImpl(UpdateDishProjectionPort updateDishProjectionPort) {
        this.updateDishProjectionPort = updateDishProjectionPort;
    }

    @Override
    public void project(DishSavedProjectionCommand command) {

        final DishProjection dish = DishProjection.create(
                command.dishId(), command.restaurantId(),
                command.stockStatus(), command.name(), command.type(), command.tags(),
                command.description(), command.price(), command.pictureURL());

        updateDishProjectionPort.update(dish);
    }
}
