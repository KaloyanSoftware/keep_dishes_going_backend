package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.OrderPlacedProjectionCommand;

public interface OrderPlacedProjector {
    void project(OrderPlacedProjectionCommand command);
}
