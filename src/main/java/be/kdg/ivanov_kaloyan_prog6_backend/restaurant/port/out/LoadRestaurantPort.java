package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Restaurant;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

public interface LoadRestaurantPort {

    Optional<Restaurant> loadBy(UUID id);
}
