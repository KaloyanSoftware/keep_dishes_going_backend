package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.DishStockStatusChangedProjectionCommand;

public interface DishStockStatusChangeProjector {
    void project(DishStockStatusChangedProjectionCommand command);
}
