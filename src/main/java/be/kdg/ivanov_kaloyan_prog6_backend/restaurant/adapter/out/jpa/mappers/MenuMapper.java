package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.MenuJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.*;
import org.mapstruct.*;
import java.util.Map;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {DishMapper.class})
public interface MenuMapper {

    @ObjectFactory
    default Menu createMenu(MenuJpaEntity jpa, @Context DishMapper dishMapper) {
        RestaurantId restaurantId = RestaurantId.of(jpa.getRestaurantId());
        Map<UUID, Dish> dishes = dishMapper.toDomainMap(jpa.getDishes());
        MenuId menuId = MenuId.of(jpa.getId());
        return Menu.rehydrate(menuId, restaurantId, dishes, jpa.getPublishedCount());
    }

    default Menu toDomain(MenuJpaEntity jpa, @Context DishMapper dishMapper) {
        return createMenu(jpa, dishMapper);
    }

    @Mapping(target = "id", expression = "java(domain.getId().id())")
    @Mapping(target = "dishes", ignore = true)
    @Mapping(target = "restaurantId", expression = "java(domain.getRestaurantId().id())")
    @Mapping(target = "publishedCount", source = "publishedCount")
    MenuJpaEntity toJpa(Menu domain, @Context DishMapper dishMapper);

    @AfterMapping
    default void linkDishes(@MappingTarget MenuJpaEntity jpa, Menu domain, @Context DishMapper dishMapper) {
        var dishJpaList = dishMapper.toJpaList(domain.getDishes());
        jpa.setDishes(dishJpaList);
    }
}
