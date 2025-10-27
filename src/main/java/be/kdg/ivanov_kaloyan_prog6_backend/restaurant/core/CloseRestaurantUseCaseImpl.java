package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Restaurant;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions.RestaurantNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.CloseRestaurantCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.CloseRestaurantUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadRestaurantPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.UpdateRestaurantPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class CloseRestaurantUseCaseImpl implements CloseRestaurantUseCase {
    private final LoadRestaurantPort loadRestaurantPort;

    private final List<UpdateRestaurantPort> updateRestaurantPorts;

    public CloseRestaurantUseCaseImpl(final LoadRestaurantPort loadRestaurantPort,
                                      final List<UpdateRestaurantPort> updateRestaurantPorts) {
        this.loadRestaurantPort = loadRestaurantPort;
        this.updateRestaurantPorts = updateRestaurantPorts;
    }

    @Override
    public Restaurant close(CloseRestaurantCommand command) {
        final Restaurant restaurant = loadRestaurantPort.loadBy(command.restaurantId()).orElseThrow(
                () -> new RestaurantNotFoundException("Restaurant with id: " + command.restaurantId() + " not found!")
        );

        restaurant.close();

        this.updateRestaurantPorts.forEach(port -> port.update(restaurant));

        restaurant.commitEvents();

        return restaurant;
    }
}
