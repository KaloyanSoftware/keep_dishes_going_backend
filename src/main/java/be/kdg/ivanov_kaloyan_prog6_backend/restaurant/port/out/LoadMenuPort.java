package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Menu;
import java.util.Optional;
import java.util.UUID;

public interface LoadMenuPort {

    Optional<Menu> loadById(UUID id);
}
