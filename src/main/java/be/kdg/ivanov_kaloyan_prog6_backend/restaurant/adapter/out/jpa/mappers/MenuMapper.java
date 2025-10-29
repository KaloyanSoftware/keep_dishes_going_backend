package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers;

import be.kdg.ivanov_kaloyan_prog6_backend.common.events.*;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.MenuEventJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.MenuJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.*;
import org.mapstruct.*;
import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {DishMapper.class})
public interface MenuMapper {

    @Mapping(target = "id", expression = "java(domain.getId().id())")
    @Mapping(target = "restaurantId", expression = "java(domain.getRestaurantId().id())")
    @Mapping(target = "dishes", ignore = true)
    @Mapping(target = "events", expression = "java(toJpaEvents(domain.getDomainEvents(), domain.getRestaurantId().id()))")
    MenuJpaEntity toJpa(Menu domain, @Context DishMapper dishMapper);

    @AfterMapping
    default void linkDishes(@MappingTarget MenuJpaEntity jpa, Menu domain, @Context DishMapper dishMapper) {
        var dishJpaList = dishMapper.toJpaList(domain.getDishes());
        jpa.setDishes(dishJpaList);
    }

    @ObjectFactory
    default Menu createMenu(MenuJpaEntity jpa, @Context DishMapper dishMapper) {
        RestaurantId restaurantId = RestaurantId.of(jpa.getRestaurantId());
        Map<UUID, Dish> dishes = dishMapper.toDomainMap(jpa.getDishes());
        MenuId menuId = MenuId.of(jpa.getId());

        List<DomainEvent> domainEvents = jpa.getEvents() != null
                ? toDomainEvents(jpa.getEvents())
                : new ArrayList<>();

        return Menu.rehydrate(menuId, restaurantId, dishes, domainEvents);
    }

    default Menu toDomain(MenuJpaEntity jpa, @Context DishMapper dishMapper) {
        return createMenu(jpa, dishMapper);
    }

    default List<MenuEventJpaEntity> toJpaEvents(List<DomainEvent> domainEvents, UUID restaurantId) {
        if (domainEvents == null || domainEvents.isEmpty()) return new ArrayList<>();

        return domainEvents.stream()
                .map(event -> toJpaEvent(event, restaurantId))
                .collect(Collectors.toList());
    }

    default MenuEventJpaEntity toJpaEvent(DomainEvent domainEvent, UUID restaurantId) {
        return switch (domainEvent) {
            case DishPublishedEvent e -> new MenuEventJpaEntity(
                    e.id(),
                    e.eventPit(),
                    e.dishId(),
                    restaurantId,
                    e.stockStatus(),
                    e.name(),
                    e.type(),
                    e.eventCatalog().name(),
                    e.tags(),
                    e.description(),
                    e.price(),
                    e.pictureURL(),
                    true
            );

            case DishUnpublishedEvent e -> new MenuEventJpaEntity(
                    e.id(),
                    e.eventPit(),
                    e.eventCatalog().name(),
                    e.dishId(),
                    restaurantId,
                    false
            );

            case DishOutOfStockEvent e -> new MenuEventJpaEntity(
                    e.id(),
                    e.eventPit(),
                    e.eventCatalog().name(),
                    e.dishId(),
                    restaurantId,
                    e.published()
            );

            case DishBackInStockEvent e -> new MenuEventJpaEntity(
                    e.id(),
                    e.eventPit(),
                    e.eventCatalog().name(),
                    e.dishId(),
                    restaurantId,
                    e.published()
            );

            default -> throw new IllegalArgumentException("Unknown menu event type: " + domainEvent.getClass());
        };
    }

    default List<DomainEvent> toDomainEvents(List<MenuEventJpaEntity> jpaEvents) {
        if (jpaEvents == null || jpaEvents.isEmpty()) return new ArrayList<>();

        return jpaEvents.stream()
                .map(this::toDomainEvent)
                .collect(Collectors.toList());
    }

    default DomainEvent toDomainEvent(MenuEventJpaEntity e) {
        return switch (EventCatalog.valueOf(e.getEventType())) {
            case DISH_PUBLISHED -> new DishPublishedEvent(
                    e.getDishId(),
                    e.getRestaurantId(),
                    e.getStockStatus(),
                    e.getName(),
                    e.getDishType(),
                    e.getTags(),
                    e.getDescription(),
                    e.getPrice(),
                    e.getPictureURL()
            );

            case DISH_UNPUBLISHED -> new DishUnpublishedEvent(
                    e.getDishId()
            );

            case DISH_OUT_OF_STOCK -> new DishOutOfStockEvent(
                    e.getDishId(), e.isDishPublished()
            );

            case DISH_BACK_IN_STOCK -> new DishBackInStockEvent(
                    e.getDishId(), e.isDishPublished()
            );

            default -> throw new IllegalArgumentException("No menu event catalog: " + e.getEventType());
        };
    }
}
