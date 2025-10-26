package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.core;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.DishProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.DishProjectionNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.DishStockStatusChangeProjector;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.DishStockStatusChangedProjectionCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.LoadDishProjectionPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.UpdateDishProjectionPort;
import org.springframework.stereotype.Service;

@Service
public class DishStockStatusChangedProjectorImpl implements DishStockStatusChangeProjector {
    private final LoadDishProjectionPort loadDishPort;

    private final UpdateDishProjectionPort  updateDishPort;

    public DishStockStatusChangedProjectorImpl(final LoadDishProjectionPort loadDishPort,
                                               final UpdateDishProjectionPort updateDishPort) {
        this.loadDishPort = loadDishPort;
        this.updateDishPort = updateDishPort;
    }

    @Override
    public void project(DishStockStatusChangedProjectionCommand command) {
        final DishProjection dishProjection = loadDishPort.loadBy(command.dishId()).orElseThrow(
                () -> new DishProjectionNotFoundException("Dish projection with id:" + command.dishId() + " not found!")
        );

        dishProjection.changeStockStatus(command.stockStatus());

        updateDishPort.update(dishProjection);
    }
}
