package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions.RestaurantNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.MenuJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.RestaurantJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers.DishMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers.MoneyMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers.MenuMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.repositories.MenuJpaRepository;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.repositories.RestaurantJpaRepository;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Menu;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadMenuPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.UpdateMenuPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;


@Repository
@Qualifier("jpa")
@Profile("jpa")
public class MenuJpaAdapter implements LoadMenuPort, UpdateMenuPort {

    private static final Logger log = LoggerFactory.getLogger(MenuJpaAdapter.class);
    private final MenuJpaRepository menus;
    private final MenuMapper menuMapper;
    private final MoneyMapper moneyMapper;
    private final DishMapper dishMapper;



    public MenuJpaAdapter(final MenuJpaRepository menus,
                          final MenuMapper menuMapper,
                          final MoneyMapper moneyMapper,
                          final DishMapper dishMapper) {
        this.menus = menus;
        this.menuMapper = menuMapper;
        this.moneyMapper = moneyMapper;
        this.dishMapper = dishMapper;
    }

    @Override
    public Optional<Menu> loadById(UUID id) {
        return menus
                .findById(id)
                .map(jpa -> menuMapper.toDomain(jpa, moneyMapper, dishMapper));
    }

    @Override
    public Optional<Menu> loadByRestaurantId(UUID restaurantId) {
        return menus
                .getByRestaurantId(restaurantId)
                .map(jpa -> menuMapper.toDomain(jpa, moneyMapper, dishMapper));
    }

    @Override
    public Menu update(Menu menu) {

        log.error("Menu id: {}", menu.getId().toString());
        MenuJpaEntity jpa = menuMapper.toJpa(menu, moneyMapper, dishMapper );

        menus.save(jpa);

        return menuMapper.toDomain(jpa, moneyMapper, dishMapper);
    }
}
