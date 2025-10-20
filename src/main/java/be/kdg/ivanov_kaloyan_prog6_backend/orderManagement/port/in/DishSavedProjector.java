package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.DishSavedProjectionCommand;

public interface DishSavedProjector {
    void project(DishSavedProjectionCommand command);
}
