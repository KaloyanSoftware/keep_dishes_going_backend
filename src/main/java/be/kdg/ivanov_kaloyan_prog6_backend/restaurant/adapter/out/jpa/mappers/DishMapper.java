package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.DishJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.*;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.util.*;

@Mapper(componentModel = "spring", imports = {DishId.class, MenuId.class})
public interface DishMapper {

    @ObjectFactory
    default Dish createDish(DishJpaEntity jpa) {
        return new Dish(
                DishId.of(jpa.getId()),
                MenuId.of(jpa.getMenuId()),
                jpa.getState(),
                jpa.getStockStatus(),
                jpa.getName(),
                jpa.getType(),
                jpa.getTags(),
                jpa.getDescription(),
                jpa.getPrice() != null ? BigDecimal.valueOf(jpa.getPrice()) : BigDecimal.ZERO,
                jpa.getPictureUrl()
        );
    }

    default Dish toDomain(DishJpaEntity jpa) {
        return createDish(jpa);
    }

    @Mapping(target = "id", expression = "java(domain.getId().id())")
    @Mapping(target = "menu", ignore = true)
    @Mapping(target = "state", source = "state")
    @Mapping(target = "stockStatus", source = "stockStatus")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", expression = "java(domain.getPrice() != null ? domain.getPrice().doubleValue() : 0.0)")
    @Mapping(target = "pictureUrl", source = "pictureURL")
    DishJpaEntity toJpa(Dish domain);

    default List<DishJpaEntity> toJpaList(Map<UUID, Dish> dishes) {
        if (dishes == null) return Collections.emptyList();
        List<DishJpaEntity> list = new ArrayList<>(dishes.size());
        for (Dish d : dishes.values()) {
            list.add(toJpa(d));
        }
        return list;
    }

    default Map<UUID, Dish> toDomainMap(List<DishJpaEntity> entities) {
        Map<UUID, Dish> result = new HashMap<>();
        if (entities != null) {
            for (DishJpaEntity e : entities) {
                Dish d = toDomain(e);
                result.put(e.getId(), d);
            }
        }
        return result;
    }
}
