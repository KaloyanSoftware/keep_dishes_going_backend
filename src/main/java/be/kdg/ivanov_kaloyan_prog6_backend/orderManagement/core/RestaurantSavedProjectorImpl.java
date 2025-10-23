package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.core;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Location;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.RestaurantProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.RestaurantSavedProjector;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.RestaurantSavedProjectionCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.UpdateRestaurantProjectionPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RestaurantSavedProjectorImpl implements RestaurantSavedProjector {

    private final UpdateRestaurantProjectionPort  updateRestaurantProjectionPort;

    public RestaurantSavedProjectorImpl(UpdateRestaurantProjectionPort updateRestaurantProjectionPort) {
        this.updateRestaurantProjectionPort = updateRestaurantProjectionPort;
    }
    @Override
    public void project(RestaurantSavedProjectionCommand command) {
        final Location location = Location.create(command.location().street(), command.location().number(),
                command.location().postalCode(), command.location().city(), command.location().country());

        final RestaurantProjection restaurantProjection = RestaurantProjection.create(command.id(),
                location, command.email(), command.pictureURL(), command.defaultPrepTime(), command.cuisineType());

        this.updateRestaurantProjectionPort.update(restaurantProjection);
    }
}
