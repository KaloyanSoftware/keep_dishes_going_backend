package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.memory;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Restaurant;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.UpdateRestaurantPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
@Qualifier("memory")
@Profile("memory")
public class RestaurantInMemoryAdapter implements UpdateRestaurantPort {

    private final Map<UUID, Restaurant> restaurants = new HashMap<>();

    @Override
    public Restaurant update(Restaurant restaurant) {
        return restaurants.put(restaurant.getId().id(), restaurant);
    }
}
