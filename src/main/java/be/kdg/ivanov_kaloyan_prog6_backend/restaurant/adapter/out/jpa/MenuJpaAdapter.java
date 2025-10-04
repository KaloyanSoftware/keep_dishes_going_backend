package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.exceptions.RestaurantNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.MenuJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.RestaurantJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers.DishMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers.IdMoneyMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers.MenuMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.repositories.MenuJpaRepository;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.repositories.RestaurantJpaRepository;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Menu;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadMenuPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.SaveMenuPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

// MenuJpaAdapter.java
@Repository
@Qualifier("jpa")
@Profile("jpa")
public class MenuJpaAdapter implements LoadMenuPort, SaveMenuPort {

    private final MenuJpaRepository menuJpaRepository;
    private final RestaurantJpaRepository restaurantJpaRepository;
    private final MenuMapper menuMapper;
    private final IdMoneyMapper idMoneyMapper;
    private final DishMapper dishMapper;

    public MenuJpaAdapter(final MenuJpaRepository menuJpaRepository,
                          final MenuMapper menuMapper,
                          final IdMoneyMapper idMoneyMapper,
                          final DishMapper dishMapper,
                          final RestaurantJpaRepository restaurantJpaRepository) {
        this.menuJpaRepository = menuJpaRepository;
        this.menuMapper = menuMapper;
        this.idMoneyMapper = idMoneyMapper;
        this.dishMapper = dishMapper;
        this.restaurantJpaRepository = restaurantJpaRepository;
    }

    @Override
    public Optional<Menu> loadById(UUID id) {
        return menuJpaRepository
                .findById(id)
                .map(jpa -> menuMapper.toDomain(jpa, idMoneyMapper, dishMapper));
    }

    @Override
    public void save(Menu menu) {
        RestaurantJpaEntity restaurantJpa = restaurantJpaRepository.findById(menu.getRestaurantId().id()).orElseThrow(
                () -> new RestaurantNotFoundException("Can't find restaurant with id: " + menu.getRestaurantId())
        );

        MenuJpaEntity jpa = menuMapper.toJpa(menu, idMoneyMapper, dishMapper, restaurantJpa);

        menuJpaRepository.save(jpa);
    }
}
