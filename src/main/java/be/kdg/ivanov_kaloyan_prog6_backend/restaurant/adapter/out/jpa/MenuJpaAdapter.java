package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.repositories.MenuRepository;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Menu;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadMenuPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.SaveMenuPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Qualifier("jpa")
@Profile("jpa")
public class MenuJpaAdapter implements LoadMenuPort, SaveMenuPort {

    private final MenuRepository menuRepository;

    public MenuJpaAdapter(final MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public Optional<Menu> loadBy(UUID restaurantId) {
        return Optional.empty();
    }

    @Override
    public Optional<Menu> loadById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Menu save(Menu menu) {
        return null;
    }
}
