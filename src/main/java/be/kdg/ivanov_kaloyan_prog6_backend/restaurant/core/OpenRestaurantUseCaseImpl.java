package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Restaurant;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions.RestaurantNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.OpenRestaurantCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.OpenRestaurantUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadRestaurantPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.UpdateRestaurantPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class OpenRestaurantUseCaseImpl implements OpenRestaurantUseCase {

    private final LoadRestaurantPort loadRestaurantPort;

    private final List<UpdateRestaurantPort> updateRestaurantPorts;

    public OpenRestaurantUseCaseImpl(final LoadRestaurantPort loadRestaurantPort,
                                     final List<UpdateRestaurantPort> updateRestaurantPorts) {
        this.loadRestaurantPort = loadRestaurantPort;
        this.updateRestaurantPorts = updateRestaurantPorts;
    }

    @Override
    public Restaurant open(OpenRestaurantCommand command) {
        final Restaurant restaurant = loadRestaurantPort.loadBy(command.restaurantId()).orElseThrow(
                () -> new RestaurantNotFoundException("Restaurant with id: " + command.restaurantId() + " not found!")
        );

        restaurant.open();

        this.updateRestaurantPorts.forEach(port -> port.update(restaurant));
        restaurant.commitEvents();

        return restaurant;
    }
}
