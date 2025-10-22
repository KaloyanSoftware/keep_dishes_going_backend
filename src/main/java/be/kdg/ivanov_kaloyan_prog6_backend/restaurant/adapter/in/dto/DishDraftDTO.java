package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishDraft;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.FoodTag;
import java.math.BigDecimal;
import java.util.List;

public record DishDraftDTO(String id, String restaurantId, String name,
                           String type, List<String> foodTags, String description,
                           BigDecimal price, String pictureURL) {

    public static DishDraftDTO from(final DishDraft draft) {

        final List<String> tags = draft.getTags().stream()
                .map(FoodTag::name).toList();

        return new DishDraftDTO(draft.getId().id().toString(), draft.getRestaurantId().id().toString(), draft.getName(), draft.getType().name(), tags,
                draft.getDescription(), draft.getPrice(), draft.getPictureURL());
    }
}
