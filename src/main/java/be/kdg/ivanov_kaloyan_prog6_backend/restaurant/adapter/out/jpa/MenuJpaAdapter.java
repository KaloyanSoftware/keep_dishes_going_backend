package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions.RestaurantNotFoundException;
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

    private final MenuJpaRepository menus;
    private final RestaurantJpaRepository restaurants;
    private final MenuMapper menuMapper;
    private final IdMoneyMapper idMoneyMapper;
    private final DishMapper dishMapper;

    public MenuJpaAdapter(final MenuJpaRepository menus,
                          final MenuMapper menuMapper,
                          final IdMoneyMapper idMoneyMapper,
                          final DishMapper dishMapper,
                          final RestaurantJpaRepository restaurants) {
        this.menus = menus;
        this.menuMapper = menuMapper;
        this.idMoneyMapper = idMoneyMapper;
        this.dishMapper = dishMapper;
        this.restaurants = restaurants;
    }

    @Override
    public Optional<Menu> loadById(UUID id) {
        return menus
                .findById(id)
                .map(jpa -> menuMapper.toDomain(jpa, idMoneyMapper, dishMapper));
    }

    @Override
    public Optional<Menu> loadByRestaurantId(UUID restaurantId) {
        return menus
                .findByRestaurant_Id(restaurantId)
                .map(jpa -> menuMapper.toDomain(jpa, idMoneyMapper, dishMapper));
    }

    @Override
    public void save(Menu menu) {
        RestaurantJpaEntity restaurantJpa = restaurants.findById(menu.getRestaurantId().id()).orElseThrow(
                () -> new RestaurantNotFoundException("Can't find restaurant with id: " + menu.getRestaurantId())
        );

        MenuJpaEntity jpa = menuMapper.toJpa(menu, idMoneyMapper, dishMapper, restaurantJpa);

        menus.save(jpa);
    }
}
