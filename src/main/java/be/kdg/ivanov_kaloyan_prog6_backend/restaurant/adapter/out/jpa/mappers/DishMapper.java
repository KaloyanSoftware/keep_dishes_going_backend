package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.DishJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Dish;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishId;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.MenuId;
import org.mapstruct.*;
import java.util.*;

@Mapper(componentModel = "spring", uses = MoneyMapper.class, imports = {DishId.class, MenuId.class})
public interface DishMapper {

    @Mapping(target = "id", expression = "java(DishId.of(jpa.getId()))")
    @Mapping(target = "menuId", expression = "java(MenuId.of(jpa.getMenuId()))")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "stockStatus", source = "stockStatus")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", expression = "java(moneyMapper.toBigDecimal(jpa.getPrice()))")
    @Mapping(target = "pictureURL", source = "pictureUrl")
    Dish toDomain(DishJpaEntity jpa, @Context MoneyMapper moneyMapper);


    @Mapping(target = "id", expression = "java(domain.getId().id())")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "stockStatus", source = "stockStatus")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", expression = "java(moneyMapper.toDouble(domain.getPrice()))")
    @Mapping(target = "pictureUrl", source = "pictureURL")
    @Mapping(target = "menuId", expression = "java(domain.getMenuId().id())")
    DishJpaEntity toJpa(Dish domain, @Context MoneyMapper moneyMapper);

    default List<DishJpaEntity> toJpaList(Map<UUID, Dish> dishes,
                                          @Context MoneyMapper moneyMapper) {
        if (dishes == null) return Collections.emptyList();
        List<DishJpaEntity> list = new ArrayList<>(dishes.size());
        for (Dish d : dishes.values()) {
            DishJpaEntity e = toJpa(d, moneyMapper);
            list.add(e);
        }
        return list;
    }

    default Map<UUID, Dish> toDomainMap(List<DishJpaEntity> entities,
                                        @Context MoneyMapper idMoney) {
        Map<UUID, Dish> result = new HashMap<>();
        if (entities != null) {
            for (DishJpaEntity e : entities) {
                Dish d = toDomain(e, idMoney);
                result.put(e.getId(), d);
            }
        }
        return result;
    }
}
