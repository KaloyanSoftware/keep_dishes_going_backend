package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishDraft;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishType;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.FoodTag;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record DishDraftDTO(UUID draftId, UUID restaurantId,String name,
                           DishType type, List<FoodTag> tags, String description,
                           BigDecimal price, String pictureURL) {

    public static DishDraftDTO from(final DishDraft draft) {
        return new DishDraftDTO(draft.getId().id(), draft.getRestaurantId().id(), draft.getName(), draft.getType(), draft.getTags(),
                draft.getDescription(), draft.getPrice(), draft.getPictureURL());
    }
}
