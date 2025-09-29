package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Restaurant;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadRestaurantPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.SaveRestaurantPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class RestaurantInMemoryAdapter implements SaveRestaurantPort, LoadRestaurantPort {

    private static final Logger log = LoggerFactory.getLogger(RestaurantInMemoryAdapter.class);

    private final Map<UUID, Restaurant> restaurants = new HashMap<>();

    @Override
    public void save(Restaurant restaurant) {
        restaurants.put(restaurant.retrieveRestaurantId().id(), restaurant);

        log.error(restaurant.toString());
        log.error("Number of registered restaurants: {}", restaurants.size());
    }

    @Override
    public Optional<Restaurant> loadBy(UUID id) {
        return Optional.ofNullable(this.restaurants.get(id));
    }
}
