package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.request;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishType;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.FoodTag;
import java.util.List;
import java.util.UUID;

public record CreateDishAsDraftRequest(UUID restaurantId, String name, DishType type, List<FoodTag> tags,
                                       String description, Double price, String pictureURL) {
}
