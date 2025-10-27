package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.RestaurantProjection;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadRestaurantProjectionPort {
    List<RestaurantProjection> loadAll();

    Optional<RestaurantProjection> loadBy(UUID restaurantId);
}
