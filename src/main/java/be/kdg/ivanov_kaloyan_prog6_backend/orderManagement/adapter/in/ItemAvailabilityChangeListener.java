package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.common.events.DishBackInStockEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.common.events.DishOutOfStockEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.common.events.DishPublishedEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.common.events.DishUnpublishedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ItemAvailabilityChangeListener {

    private static final Logger log = LoggerFactory.getLogger(ItemAvailabilityChangeListener.class);


    @EventListener(DishOutOfStockEvent.class)
    public void itemAvailabilityChanged(DishOutOfStockEvent event) {
        log.error("Item out of stock event received");

    }

    @EventListener(DishBackInStockEvent.class)
    public void itemAvailabilityChanged(DishBackInStockEvent event) {
        log.error("Item back in stock event received");

    }

    @EventListener(DishUnpublishedEvent.class)
    public void itemAvailabilityChanged(DishUnpublishedEvent event) {
        log.error("Item unpublished event received");

    }

    @EventListener(DishPublishedEvent.class)
    public void itemAvailabilityChanged(DishPublishedEvent event) {
        log.error("Item published event received");

    }
}
