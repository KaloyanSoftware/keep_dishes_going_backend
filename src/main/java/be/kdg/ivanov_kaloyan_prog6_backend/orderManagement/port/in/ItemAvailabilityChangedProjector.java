package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.ItemPublishStatusChangedProjectionCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.ItemStockStatusChangedProjectionCommand;

public interface ItemAvailabilityChangedProjector {
    void project(ItemStockStatusChangedProjectionCommand command);

    void project(ItemPublishStatusChangedProjectionCommand command);
}
