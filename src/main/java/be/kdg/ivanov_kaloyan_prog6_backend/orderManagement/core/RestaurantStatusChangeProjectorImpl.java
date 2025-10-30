package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.core;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.RestaurantProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.RestaurantProjectionNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.RestaurantStatusChangeProjector;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.RestaurantProjectionChangeStatusCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.LoadRestaurantProjectionPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.UpdateRestaurantProjectionPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RestaurantStatusChangeProjectorImpl implements RestaurantStatusChangeProjector {

    private final LoadRestaurantProjectionPort loadRestaurantProjectionPort;

    private final UpdateRestaurantProjectionPort updateRestaurantProjectionPort;

    public RestaurantStatusChangeProjectorImpl(final LoadRestaurantProjectionPort loadRestaurantProjectionPort,
                                               final UpdateRestaurantProjectionPort  updateRestaurantProjectionPort) {
        this.loadRestaurantProjectionPort = loadRestaurantProjectionPort;
        this.updateRestaurantProjectionPort = updateRestaurantProjectionPort;
    }

    @Override
    public void project(RestaurantProjectionChangeStatusCommand command) {
        final RestaurantProjection projection = loadRestaurantProjectionPort.loadBy(command.restaurantId()).orElseThrow(
                () -> new RestaurantProjectionNotFoundException("Restaurant projection with id: " + command.restaurantId() + "not found!")
        );

        boolean isOpen = command.isOpen();

        if (isOpen) {
            projection.open();
        } else {
            projection.close();
        }

        this.updateRestaurantProjectionPort.update(projection);
    }
}
