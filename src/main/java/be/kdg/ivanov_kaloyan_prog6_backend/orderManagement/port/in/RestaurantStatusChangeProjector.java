package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.RestaurantProjectionChangeStatusCommand;

public interface RestaurantStatusChangeProjector {
    void project(RestaurantProjectionChangeStatusCommand command);
}
