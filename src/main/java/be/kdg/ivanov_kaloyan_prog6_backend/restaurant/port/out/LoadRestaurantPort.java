package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Restaurant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadRestaurantPort {

    Optional<Restaurant> loadBy(UUID id);

    Optional<Restaurant> loadByOwner(UUID ownerId);

    boolean existsByIdAndOwnerId(UUID ownerId, UUID restaurantId);
}
