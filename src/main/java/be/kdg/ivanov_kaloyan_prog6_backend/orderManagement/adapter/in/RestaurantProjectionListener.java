package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.common.RestaurantCloseEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.common.RestaurantOpenEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.common.SaveRestaurantEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.dto.LocationDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.RestaurantSavedProjector;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.RestaurantStatusChangeProjector;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.RestaurantProjectionChangeStatusCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.RestaurantSavedProjectionCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class RestaurantProjectionListener {
    private final RestaurantSavedProjector projector;

    private final RestaurantStatusChangeProjector restaurantStatusChangeProjector;

    private static final Logger log = LoggerFactory.getLogger(RestaurantProjectionListener.class);

    public RestaurantProjectionListener(final RestaurantSavedProjector projector,
                                        final RestaurantStatusChangeProjector restaurantStatusChangeProjector) {
        this.projector = projector;
        this.restaurantStatusChangeProjector = restaurantStatusChangeProjector;
    }

    @ApplicationModuleListener
    public void restaurantCreated(SaveRestaurantEvent event) {
        log.error("Save restaurant with id: {} event received!", event.restaurantId());

        final LocationDTO location = LocationDTO.create(event.address().street(), event.address().number(),
                event.address().postalCode(), event.address().city(), event.address().country());

        final RestaurantSavedProjectionCommand command = new RestaurantSavedProjectionCommand(event.restaurantId(), location,
                event.email(),  event.pictureURL(), event.defaultPrepTime(), event.cuisineType());

        this.projector.project(command);
    }

    @ApplicationModuleListener
    public void onRestaurantOpen(RestaurantOpenEvent event) {
        log.error("Open restaurant with id: {} event received!", event.restaurantId());

        final RestaurantProjectionChangeStatusCommand command = new RestaurantProjectionChangeStatusCommand(event.restaurantId(), true);

        this.restaurantStatusChangeProjector.project(command);
    }

    @ApplicationModuleListener
    public void onRestaurantClose(RestaurantCloseEvent event) {
        log.error("Close restaurant with id: {} event received!", event.restaurantId());

        final RestaurantProjectionChangeStatusCommand command = new RestaurantProjectionChangeStatusCommand(event.restaurantId(), false);

        this.restaurantStatusChangeProjector.project(command);
    }
}
