package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import java.math.BigDecimal;
import java.util.UUID;

public record Item(UUID restaurantId,
                   UUID dishId,
                   String name,
                   BigDecimal price,
                   Integer quantity,
                   String pictureURL) {

    @Override
    public String toString() {
        return String.format(
                "Item{name='%s', price=%.2f, quantity=%d, dishId=%s}",
                name, price, quantity, dishId
        );
    }
}
