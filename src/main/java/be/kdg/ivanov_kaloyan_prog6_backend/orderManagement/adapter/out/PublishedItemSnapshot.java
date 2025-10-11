package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out;

import java.math.BigDecimal;
import java.util.UUID;

public record PublishedItemSnapshot(UUID dishId,
                                    UUID restaurantId,
                                    String name,
                                    BigDecimal price,
                                    String pictureURL,
                                    boolean outOfStock) {
}
