package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.DishJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.MenuJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers.DishMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers.MenuMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.repositories.MenuJpaRepository;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Menu;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadMenuPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.UpdateMenuPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
@Qualifier("jpa")
public class MenuJpaAdapter implements LoadMenuPort, UpdateMenuPort {

    private final MenuJpaRepository menus;
    private final MenuMapper menuMapper;
    private final DishMapper dishMapper;

    public MenuJpaAdapter(final MenuJpaRepository menus,
                          final MenuMapper menuMapper,
                          final DishMapper dishMapper) {
        this.menus = menus;
        this.menuMapper = menuMapper;
        this.dishMapper = dishMapper;
    }

    @Override
    public Optional<Menu> loadById(UUID id) {
        return menus
                .findById(id)
                .map(jpa -> menuMapper.toDomain(jpa, dishMapper));
    }

    @Override
    public Optional<Menu> loadByRestaurantId(UUID restaurantId) {
        return menus
                .getByRestaurantId(restaurantId)
                .map(jpa -> menuMapper.toDomain(jpa, dishMapper));
    }

    @Override
    public Menu update(Menu menu) {
        final MenuJpaEntity incoming = menuMapper.toJpa(menu, dishMapper);
        final MenuJpaEntity managed = menus.findById(incoming.getId())
                .orElseThrow(() -> new IllegalArgumentException("Menu not found: " + incoming.getId()));

        managed.setRestaurantId(incoming.getRestaurantId());
        managed.setEvents(incoming.getEvents());

        syncDishes(managed, incoming.getDishes());

        final MenuJpaEntity savedJpa = menus.save(managed);
        return menuMapper.toDomain(savedJpa, dishMapper);
    }

    private void syncDishes(MenuJpaEntity managed, List<DishJpaEntity> incomingDishes) {
        final Map<UUID, DishJpaEntity> incomingById = (incomingDishes == null ? List.<DishJpaEntity>of() : incomingDishes)
                .stream()
                .collect(Collectors.toMap(DishJpaEntity::getId, Function.identity()));

        managed.getDishes().removeIf(existing -> !incomingById.containsKey(existing.getId()));

        final Map<UUID, DishJpaEntity> managedById = managed.getDishes().stream()
                .collect(Collectors.toMap(DishJpaEntity::getId, Function.identity()));

        for (DishJpaEntity incomingDish : incomingById.values()) {
            final DishJpaEntity managedDish = managedById.get(incomingDish.getId());

            if (managedDish == null) {
                managed.addDish(incomingDish);
                continue;
            }

            copyDishFields(managedDish, incomingDish);
        }
    }

    private void copyDishFields(DishJpaEntity target, DishJpaEntity source) {
        target.setState(source.getState());
        target.setStockStatus(source.getStockStatus());
        target.setName(source.getName());
        target.setType(source.getType());
        target.setDescription(source.getDescription());
        target.setPrice(source.getPrice());
        target.setPictureUrl(source.getPictureUrl());
        target.setTags(source.getTags() == null ? null : new ArrayList<>(source.getTags()));
    }
}
