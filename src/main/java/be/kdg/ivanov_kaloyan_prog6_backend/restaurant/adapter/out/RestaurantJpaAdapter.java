package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.entities.OwnerJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.entities.RestaurantJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.mappers.RestaurantMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.repositories.OwnerJpaRepository;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.repositories.RestaurantJpaRepository;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Restaurant;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadRestaurantPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.SaveRestaurantPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Qualifier("jpa")
@Profile("jpa")
public class RestaurantJpaAdapter implements SaveRestaurantPort, LoadRestaurantPort {

    private final RestaurantJpaRepository restaurants;

    private final OwnerJpaRepository owners;

    private final RestaurantMapper mapper;



    public RestaurantJpaAdapter(final RestaurantJpaRepository restaurants,
                                final RestaurantMapper mapper,
                                final OwnerJpaRepository owners) {
        this.restaurants = restaurants;
        this.mapper = mapper;
        this.owners = owners;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        RestaurantJpaEntity restaurantJpa = mapper.toJpa(restaurant);

        OwnerJpaEntity ownerJpa = owners.getReferenceById(restaurant.getOwnerId().id());

        restaurantJpa.setOwner(ownerJpa);

        RestaurantJpaEntity restaurantJpa1 = restaurants.save(restaurantJpa);

        return mapper.toDomain(restaurantJpa1);
    }

    @Override
    public Optional<Restaurant> loadBy(UUID id) {
        return this.restaurants.findById(id).map(mapper::toDomain);
    }
}
