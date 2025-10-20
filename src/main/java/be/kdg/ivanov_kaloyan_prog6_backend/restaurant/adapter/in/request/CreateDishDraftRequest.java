package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.request;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishType;
import java.util.UUID;

public record CreateDishDraftRequest(UUID restaurantId, UUID dishId, String name, DishType type, String[] foodTags,
                                     String description, Double price, String pictureURL) {
}
