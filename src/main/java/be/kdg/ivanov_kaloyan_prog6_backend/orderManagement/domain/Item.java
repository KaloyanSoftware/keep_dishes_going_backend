package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import java.math.BigDecimal;
import java.util.UUID;

public record Item(UUID restaurantId,
                   UUID dishId,
                   String name,
                   BigDecimal price,
                   Integer quantity,
                   String pictureURL,
                   boolean outOfStock) {
}
