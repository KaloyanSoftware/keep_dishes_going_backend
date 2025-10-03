package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.DishDraftJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishDraft;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishDraftId;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.RestaurantId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {RestaurantId.class, DishDraftId.class})
public interface DishDraftMapper {

    @Mapping(target = "id", expression = "java(draft.getId().id())")
    DishDraftJpaEntity toJpa(DishDraft draft);

    @Mapping(target = "id", expression = "java(DishDraftId.of(draft.getId()))")
    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(draft.getRestaurant().getId()))")
    DishDraft toDomain(DishDraftJpaEntity draft);
}
