package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.memory;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Menu;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadMenuPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.SaveMenuPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
@Qualifier("memory")
@Profile("memory")
public class MenuInMemoryAdapter implements SaveMenuPort, LoadMenuPort {

    private final Map<UUID, Menu> menus = new HashMap<>();

    @Override
    public Menu save(Menu menu) {
        return menus.put(menu.getId().id(), menu);
    }

    @Override
    public Optional<Menu> loadBy(UUID restaurantId) {
        return menus.values().stream()
                .filter(m -> m.getRestaurantId().id().equals(restaurantId))
                .findFirst();
    }

    @Override
    public Optional<Menu> loadById(UUID id) {
        return Optional.empty();
    }
}
