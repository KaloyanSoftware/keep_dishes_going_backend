package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.DishDraftJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.*;
import org.mapstruct.*;
import java.math.BigDecimal;

@Mapper(componentModel = "spring", imports = {RestaurantId.class, DishDraftId.class, DishId.class})
public interface DishDraftMapper {

    @Mapping(target = "id", expression = "java(draft.getId().id())")
    @Mapping(target = "restaurantId", expression = "java(draft.getRestaurantId().id())")
    @Mapping(target = "dishId", expression = "java(draft.getDishId() != null ? draft.getDishId().id() : null)")
    @Mapping(target = "pictureUrl", source = "pictureURL")
    @Mapping(target = "draftTags", source = "tags")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", expression = "java(draft.getPrice() != null ? draft.getPrice().doubleValue() : 0.0)")
    DishDraftJpaEntity toJpa(DishDraft draft);

    @ObjectFactory
    default DishDraft createDishDraft(DishDraftJpaEntity jpa) {
        return new DishDraft(
                DishDraftId.of(jpa.getId()),
                RestaurantId.of(jpa.getRestaurantId()),
                jpa.getDishId() != null ? DishId.of(jpa.getDishId()) : null,
                jpa.getName(),
                jpa.getType(),
                jpa.getDraftTags(),
                jpa.getDescription(),
                jpa.getPrice() != null ? BigDecimal.valueOf(jpa.getPrice()) : BigDecimal.ZERO,
                jpa.getPictureUrl()
        );
    }

    default DishDraft toDomain(DishDraftJpaEntity jpa) {
        return createDishDraft(jpa);
    }
}
