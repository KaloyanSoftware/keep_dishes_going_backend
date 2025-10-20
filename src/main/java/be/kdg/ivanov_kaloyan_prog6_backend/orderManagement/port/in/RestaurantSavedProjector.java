package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.RestaurantSavedProjectionCommand;

public interface RestaurantSavedProjector {
    void project(RestaurantSavedProjectionCommand command);
}
