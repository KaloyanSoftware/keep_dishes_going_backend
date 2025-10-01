package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Restaurant;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.SaveRestaurantPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("jpa")
public class RestaurantJpaAdapter implements SaveRestaurantPort {

    private static final Logger log = LoggerFactory.getLogger(RestaurantJpaAdapter.class);
    private final RestaurantJpaRepository restaurants;

    private final OwnerJpaRepository owners;

    private final RestaurantMapper restaurantMapper;



    public RestaurantJpaAdapter(RestaurantJpaRepository restaurants,
                                RestaurantMapper restaurantMapper,
                                OwnerJpaRepository owners) {
        this.restaurants = restaurants;
        this.restaurantMapper = restaurantMapper;
        this.owners = owners;
    }

    @Override
    public void save(Restaurant restaurant) {
        RestaurantJpaEntity restaurantJpa = restaurantMapper.toJpa(restaurant);

        OwnerJpaEntity ownerJpa = owners.getReferenceById(restaurant.getOwnerId().id());


        restaurantJpa.setOwner(ownerJpa);

        log.error("Restaurant owner domain id from request: {}", restaurant.getOwnerId().id());
        log.error("Restaurant owner jpa entity id: {}", ownerJpa.getId());

        restaurants.save(restaurantJpa);
    }
}
