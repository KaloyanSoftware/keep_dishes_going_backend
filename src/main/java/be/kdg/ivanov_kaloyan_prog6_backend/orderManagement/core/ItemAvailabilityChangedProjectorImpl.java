package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.core;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.ItemAvailability;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.ItemAvailabilityChangedProjector;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.ItemAvailabilityChangedProjectionCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.LoadItemAvailabilityPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.UpdateItemAvailabilityPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ItemAvailabilityChangedProjectorImpl implements ItemAvailabilityChangedProjector {

    private static final Logger logger = LoggerFactory.getLogger(ItemAvailabilityChangedProjectorImpl.class);

    private final UpdateItemAvailabilityPort updateItemAvailabilityPort;

    private final LoadItemAvailabilityPort loadItemAvailabilityPort;

    public ItemAvailabilityChangedProjectorImpl(final UpdateItemAvailabilityPort updateItemAvailabilityPort,
                                                final LoadItemAvailabilityPort loadItemAvailabilityPort) {
        this.updateItemAvailabilityPort = updateItemAvailabilityPort;
        this.loadItemAvailabilityPort = loadItemAvailabilityPort;
    }

    @Override
    public void project(ItemAvailabilityChangedProjectionCommand command) {
        final ItemAvailability itemAvailability = loadItemAvailabilityPort.loadBy(command.dishId()).orElse(
                new ItemAvailability(command.restaurantId(), command.dishId(), command.published(), command.inStock())
        );

        itemAvailability.setInStock(command.inStock());
        itemAvailability.setPublished(command.published());

        ItemAvailability updatedItemAvailability = updateItemAvailabilityPort.update(itemAvailability);

        logger.info("Updated stock status item availability: {}",  updatedItemAvailability );

    }

}
