package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.ChangeOrderProjectionDeliveryStatusCommand;

public interface ChangeOrderDeliveryStatusProjector {
    void changeDeliveryStatus(ChangeOrderProjectionDeliveryStatusCommand command);
}
