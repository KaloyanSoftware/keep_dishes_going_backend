package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.ItemAvailabilityChangedProjectionCommand;

public interface ItemAvailabilityChangedProjector {
    void project(ItemAvailabilityChangedProjectionCommand command);
}
