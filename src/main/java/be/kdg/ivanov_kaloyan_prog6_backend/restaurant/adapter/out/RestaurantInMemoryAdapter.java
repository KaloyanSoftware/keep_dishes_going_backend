package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Restaurant;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.SaveRestaurantPort;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class RestaurantInMemoryAdapter implements SaveRestaurantPort {

    private final Map<UUID, Restaurant> restaurants = new HashMap<>();

    @Override
    public void save(Restaurant restaurant) {
        restaurants.put(restaurant.retrieveRestaurantId().id(), restaurant);
    }
}
