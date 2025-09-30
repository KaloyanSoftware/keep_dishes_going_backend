package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Restaurant;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.SaveRestaurantPort;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantJpaAdapter implements SaveRestaurantPort {

    private final RestaurantJpaRepository restaurants;

    private final RestaurantMapper restaurantMapper;

    public RestaurantJpaAdapter(RestaurantJpaRepository restaurants,
                                RestaurantMapper restaurantMapper) {
        this.restaurants = restaurants;
        this.restaurantMapper = restaurantMapper;
    }

    @Override
    public void save(Restaurant restaurant) {
        restaurants.save(restaurantMapper.toJpa(restaurant));
    }
}
