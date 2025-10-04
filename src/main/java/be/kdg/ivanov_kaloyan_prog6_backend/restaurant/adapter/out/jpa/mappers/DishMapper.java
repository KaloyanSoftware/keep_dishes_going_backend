package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.DishJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.MenuJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Dish;
import org.mapstruct.*;
import java.util.*;

@Mapper(componentModel = "spring", uses = IdMoneyMapper.class)
public interface DishMapper {

    @Mapping(target = "id", expression = "java(idMoney.toDishId(jpa.getId()))")
    @Mapping(target = "visibility", source = "visibility")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", expression = "java(idMoney.toBigDecimal(jpa.getPrice()))")
    @Mapping(target = "pictureURL", source = "pictureUrl")
    default Dish toDomain(DishJpaEntity jpa, @Context IdMoneyMapper idMoney) {
        return Dish.rehydrate(
                idMoney.toDishId(jpa.getId()),
                jpa.getVisibility(),
                jpa.getName(),
                jpa.getType(),
                jpa.getTags(),
                jpa.getDescription(),
                idMoney.toBigDecimal(jpa.getPrice()),
                jpa.getPictureUrl()
        );
    }


    @Mapping(target = "id", expression = "java(idMoney.toUUID(domain.getId()))")
    @Mapping(target = "visibility", source = "visibility")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", expression = "java(idMoney.toDouble(domain.getPrice()))")
    @Mapping(target = "pictureUrl", source = "pictureURL")
    @Mapping(target = "menu", ignore = true)
    DishJpaEntity toJpa(Dish domain, @Context IdMoneyMapper idMoney);

    default List<DishJpaEntity> toJpaList(Map<
            UUID, Dish> dishes, MenuJpaEntity menuJpa, @Context IdMoneyMapper idMoney) {
        if (dishes == null) return Collections.emptyList();
        List<DishJpaEntity> list = new ArrayList<>(dishes.size());
        for (Dish d : dishes.values()) {
            DishJpaEntity e = toJpa(d, idMoney);
            e.setMenu(menuJpa);
            list.add(e);
        }
        return list;
    }

    default Map<UUID, Dish> toDomainMap(List<DishJpaEntity> entities, @Context IdMoneyMapper idMoney) {
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
