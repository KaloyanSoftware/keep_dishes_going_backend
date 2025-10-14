
package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.MenuJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Dish;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Menu;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.MenuId;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.RestaurantId;
import org.mapstruct.*;
import java.util.Map;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = { MoneyMapper.class, DishMapper.class, RestaurantMapper.class})
public interface MenuMapper {

    @ObjectFactory
    default Menu newMenu(MenuJpaEntity jpa, @Context MoneyMapper moneyMapper, @Context DishMapper dishMapper) {
        RestaurantId restaurantId = RestaurantId.of(jpa.getRestaurantId());
        Map<UUID, Dish> dishes =
                dishMapper.toDomainMap(jpa.getDishes(), moneyMapper);
        MenuId mid = MenuId.of(jpa.getId());
        return Menu.rehydrate(mid, restaurantId, dishes);
    }

    default Menu toDomain(MenuJpaEntity jpa, @Context MoneyMapper moneyMapper, @Context DishMapper dishMapper) {
        return newMenu(jpa, moneyMapper, dishMapper);
    }

    @Mapping(target = "id", expression = "java(domain.getId().id())")
    @Mapping(target = "dishes", ignore = true)
    @Mapping(target = "restaurantId", expression = "java(domain.getRestaurantId().id())")
    MenuJpaEntity toJpa(Menu domain, @Context MoneyMapper moneyMapper, @Context DishMapper dishMapper);

    @AfterMapping
    default void linkDishes(@MappingTarget MenuJpaEntity jpa,
                            Menu domain, @Context MoneyMapper moneyMapper,
                            @Context DishMapper dishMapper) {
        var dishJpaList = dishMapper.toJpaList(domain.getDishes(), moneyMapper);
        jpa.setDishes(dishJpaList);
    }
}
