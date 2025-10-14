package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.DishDraftJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishDraft;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishDraftId;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishId;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.RestaurantId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {RestaurantId.class, DishDraftId.class, DishId.class})
public interface DishDraftMapper {

    @Mapping(target = "id", expression = "java(draft.getId().id())")
    @Mapping(target = "pictureUrl", source = "pictureURL")
    @Mapping(target = "draftTags", source = "tags")
    @Mapping(target = "dishId",
            expression = "java(draft.getDishId() != null ? draft.getDishId().id() : null)")
    @Mapping(target = "restaurantId", expression = "java(draft.getRestaurantId().id())")
    DishDraftJpaEntity toJpa(DishDraft draft);

    @Mapping(target = "id", expression = "java(DishDraftId.of(draft.getId()))")
    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(draft.getRestaurantId()))")
    @Mapping(target = "pictureURL", source = "pictureUrl")
    @Mapping(target = "tags", source = "draftTags")
    @Mapping(target = "dishId",
            expression = "java(draft.getDishId() != null ? DishId.of(draft.getDishId()) : null)")
    DishDraft toDomain(DishDraftJpaEntity draft);
}
