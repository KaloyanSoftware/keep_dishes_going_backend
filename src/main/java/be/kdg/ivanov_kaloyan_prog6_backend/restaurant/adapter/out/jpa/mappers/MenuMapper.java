
package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.MenuJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.RestaurantJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Dish;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Menu;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.MenuId;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.RestaurantId;
import org.mapstruct.*;
import java.util.Map;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = { IdMoneyMapper.class, DishMapper.class, RestaurantMapper.class})
public interface MenuMapper {

    @ObjectFactory
    default Menu newMenu(MenuJpaEntity jpa, @Context IdMoneyMapper idMoney, @Context DishMapper dishMapper) {
        RestaurantJpaEntity r = jpa.getRestaurant();
        RestaurantId restaurantId = RestaurantId.of(r.getId());
        Map<UUID, Dish> dishes =
                dishMapper.toDomainMap(jpa.getDishes(), idMoney);
        MenuId mid = MenuId.of(jpa.getId());
        return Menu.rehydrate(mid, restaurantId, dishes);
    }

    default Menu toDomain(MenuJpaEntity jpa, @Context IdMoneyMapper idMoney, @Context DishMapper dishMapper) {
        return newMenu(jpa, idMoney, dishMapper);
    }

    @Mapping(target = "id", expression = "java(idMoney.toUUID(domain.getId()))")
    @Mapping(target = "dishes", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    MenuJpaEntity toJpa(Menu domain, @Context IdMoneyMapper idMoney, @Context DishMapper dishMapper,
                        RestaurantJpaEntity restaurantJpaEntity);

    @AfterMapping
    default void linkDishes(@MappingTarget MenuJpaEntity jpa,
                            Menu domain,
                            @Context IdMoneyMapper idMoney,
                            @Context DishMapper dishMapper,
                            RestaurantJpaEntity restaurantJpaEntity) {
        var dishJpaList = dishMapper.toJpaList(domain.getDishes(), jpa, idMoney);
        jpa.setDishes(dishJpaList);
        jpa.setRestaurant(restaurantJpaEntity);
    }
}
