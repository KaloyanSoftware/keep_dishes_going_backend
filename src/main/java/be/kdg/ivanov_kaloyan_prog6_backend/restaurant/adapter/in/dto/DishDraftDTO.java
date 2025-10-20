package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishDraft;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishType;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.FoodTag;
import java.math.BigDecimal;
import java.util.UUID;

public record DishDraftDTO(UUID id, UUID restaurantId, String name,
                           DishType type, String[] foodTags, String description,
                           BigDecimal price, String pictureURL) {

    public static DishDraftDTO from(final DishDraft draft) {

        final String[] tags = draft.getTags().stream()
                .map(FoodTag::name)
                .toArray(String[]::new);

        return new DishDraftDTO(draft.getId().id(), draft.getRestaurantId().id(), draft.getName(), draft.getType(), tags,
                draft.getDescription(), draft.getPrice(), draft.getPictureURL());
    }
}
