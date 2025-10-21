package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.DishJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.*;
import org.mapstruct.*;

import java.util.*;

@Mapper(componentModel = "spring", uses = MoneyMapper.class, imports = {DishId.class, MenuId.class})
public interface DishMapper {

    @ObjectFactory
    default Dish createDish(DishJpaEntity jpa, @Context MoneyMapper moneyMapper) {
        return new Dish(
                DishId.of(jpa.getId()),
                MenuId.of(jpa.getMenuId()),
                jpa.getState(),
                jpa.getStockStatus(),
                jpa.getName(),
                jpa.getType(),
                jpa.getTags(),
                jpa.getDescription(),
                moneyMapper.toBigDecimal(jpa.getPrice()),
                jpa.getPictureUrl()
        );
    }

    default Dish toDomain(DishJpaEntity jpa, @Context MoneyMapper moneyMapper) {
        return createDish(jpa, moneyMapper);
    }

    @Mapping(target = "id", expression = "java(domain.getId().id())")
    @Mapping(target = "menuId", expression = "java(domain.getMenuId().id())")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "stockStatus", source = "stockStatus")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", expression = "java(moneyMapper.toDouble(domain.getPrice()))")
    @Mapping(target = "pictureUrl", source = "pictureURL")
    DishJpaEntity toJpa(Dish domain, @Context MoneyMapper moneyMapper);

    default List<DishJpaEntity> toJpaList(Map<UUID, Dish> dishes, @Context MoneyMapper moneyMapper) {
        if (dishes == null) return Collections.emptyList();
        List<DishJpaEntity> list = new ArrayList<>(dishes.size());
        for (Dish d : dishes.values()) {
            list.add(toJpa(d, moneyMapper));
        }
        return list;
    }

    default Map<UUID, Dish> toDomainMap(List<DishJpaEntity> entities, @Context MoneyMapper moneyMapper) {
        Map<UUID, Dish> result = new HashMap<>();
        if (entities != null) {
            for (DishJpaEntity e : entities) {
                Dish d = toDomain(e, moneyMapper);
                result.put(e.getId(), d);
            }
        }
        return result;
    }
}
