package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.ItemAvailability;
import java.util.Optional;
import java.util.UUID;

public interface LoadItemAvailabilityPort {
    Optional<ItemAvailability> loadBy(UUID dishId);
}
