package be.kdg.ivanov_kaloyan_prog6_backend.common.responses;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Dish;

import java.math.BigDecimal;
import java.util.UUID;

public record PublishedDishResponse(
        UUID dishId,
        UUID restaurantId,
        String name,
        BigDecimal priceCents,
        String pictureUrl,
        boolean outOfStock
) {
}
