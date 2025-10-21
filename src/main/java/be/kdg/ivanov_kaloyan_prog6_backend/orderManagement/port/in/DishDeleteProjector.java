package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.DishUnpublishedProjectionCommand;

public interface DishDeleteProjector {
    void project(DishUnpublishedProjectionCommand command);
}
