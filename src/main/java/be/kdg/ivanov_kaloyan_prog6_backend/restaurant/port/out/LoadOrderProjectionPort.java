package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.OrderProjection;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadOrderProjectionPort {
    Optional<OrderProjection> loadBy(UUID id);

    List<OrderProjection> loadAllActiveBy(UUID restaurantId);
}
