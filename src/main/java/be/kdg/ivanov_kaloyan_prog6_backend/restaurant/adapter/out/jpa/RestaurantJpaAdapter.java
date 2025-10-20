package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.RestaurantJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers.RestaurantMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.repositories.RestaurantJpaRepository;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Restaurant;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadRestaurantPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.UpdateRestaurantPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
@Qualifier("jpa")
@Profile("jpa")
public class RestaurantJpaAdapter implements UpdateRestaurantPort, LoadRestaurantPort {

    private final RestaurantJpaRepository restaurants;

    private final RestaurantMapper mapper;



    public RestaurantJpaAdapter(final RestaurantJpaRepository restaurants,
                                final RestaurantMapper mapper) {
        this.restaurants = restaurants;
        this.mapper = mapper;
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        RestaurantJpaEntity restaurantJpa = mapper.toJpa(restaurant);

        RestaurantJpaEntity saved = restaurants.save(restaurantJpa);

        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Restaurant> loadBy(UUID id) {
        return this.restaurants.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Restaurant> loadByOwner(UUID ownerId) {
        return this.restaurants.findByOwnerId(ownerId).map(mapper::toDomain);
    }
}
