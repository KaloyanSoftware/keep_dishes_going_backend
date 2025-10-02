package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.request;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishType;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.FoodTag;
import java.math.BigDecimal;
import java.util.Set;

public record CreateDishAsDraftRequest(String name, DishType type, Set<FoodTag> tags,
                                       String description, BigDecimal price, String pictureURL) {
}
