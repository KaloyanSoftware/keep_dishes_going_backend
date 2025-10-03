package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import java.math.BigDecimal;
import java.util.EnumSet;

public record DishSnapshot(
        String name,
        DishType type,
        EnumSet<FoodTag> tags,
        String description,
        BigDecimal price,
        String pictureURL
) {}
