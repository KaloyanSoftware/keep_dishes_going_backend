package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.core;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.ItemAvailabilityChangedProjector;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.ItemPublishStatusChangedProjectionCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.ItemStockStatusChangedProjectionCommand;
import org.springframework.stereotype.Service;

@Service
public class ItemAvailabilityChangedProjectorImpl implements ItemAvailabilityChangedProjector {
    @Override
    public void project(ItemStockStatusChangedProjectionCommand command) {

    }

    @Override
    public void project(ItemPublishStatusChangedProjectionCommand command) {

    }
}
