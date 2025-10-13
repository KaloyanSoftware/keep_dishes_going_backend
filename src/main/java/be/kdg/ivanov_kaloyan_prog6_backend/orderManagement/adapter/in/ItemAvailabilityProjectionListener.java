package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.common.events.*;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.ItemAvailabilityChangedProjector;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.ItemAvailabilityChangedProjectionCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ItemAvailabilityProjectionListener {

    private static final Logger log = LoggerFactory.getLogger(ItemAvailabilityProjectionListener.class);

    private final ItemAvailabilityChangedProjector projector;

    public ItemAvailabilityProjectionListener(ItemAvailabilityChangedProjector projector) {
        this.projector = projector;
    }

    @EventListener(DishOutOfStockEvent.class)
    public void itemAvailabilityChanged(DishOutOfStockEvent event) {
        log.error("Item out of stock event received");
        this.projector.project(new ItemAvailabilityChangedProjectionCommand(event.dishId(),
                event.restaurantId(), event.published(), event.inStock()));
    }

    @EventListener(DishBackInStockEvent.class)
    public void itemAvailabilityChanged(DishBackInStockEvent event) {
        log.error("Item back in stock event received");
        this.projector.project(new ItemAvailabilityChangedProjectionCommand(event.dishId(),
                event.restaurantId(), event.published(), event.inStock()));
    }

    @EventListener(DishUnpublishedEvent.class)
    public void itemAvailabilityChanged(DishUnpublishedEvent event) {
        log.error("Item unpublished event received");
        this.projector.project(new ItemAvailabilityChangedProjectionCommand(event.dishId()
                ,event.restaurantId(), event.published(), event.inStock()
        ));
    }

    @EventListener(DishPublishedEvent.class)
    public void itemAvailabilityChanged(DishPublishedEvent event) {
        log.error("Item published event received");
        this.projector.project(new ItemAvailabilityChangedProjectionCommand(event.dishId()
                ,event.restaurantId(), event.published(), event.inStock()
        ));
    }
}
