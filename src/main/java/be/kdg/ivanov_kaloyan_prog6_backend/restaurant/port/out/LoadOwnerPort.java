package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Owner;
import java.util.Optional;
import java.util.UUID;

public interface LoadOwnerPort {
    Optional<Owner> loadBy(UUID id);
}
