package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.common.events.SaveRestaurantEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.dto.LocationDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.RestaurantSavedProjector;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.RestaurantSavedProjectionCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RestaurantProjectionListener {
    private final RestaurantSavedProjector projector;

    private static final Logger log = LoggerFactory.getLogger(RestaurantProjectionListener.class);

    public RestaurantProjectionListener(RestaurantSavedProjector projector) {
        this.projector = projector;
    }

    @EventListener(SaveRestaurantEvent.class)
    public void restaurantCreated(SaveRestaurantEvent event) {
        log.error("Save restaurant with id: {} event received!", event.restaurantId());

        final LocationDTO location = LocationDTO.create(event.address().street(), event.address().number(),
                event.address().postalCode(), event.address().city(), event.address().country());

        final RestaurantSavedProjectionCommand command = new RestaurantSavedProjectionCommand(event.restaurantId(), location,
                event.email(),  event.pictureURL(), event.defaultPrepTime(), event.cuisineType());

        this.projector.project(command);
    }
}
