package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DeliveryInfo;
import org.springframework.util.Assert;
import java.util.Map;
import java.util.UUID;

public record OrderPlacedProjectionCommand(UUID orderId, UUID restaurantId, Map<String, Integer> orderLines, DeliveryInfo deliveryInfo) {
    public OrderPlacedProjectionCommand {
        Assert.notNull(orderId, "Order ID must not be null");
        Assert.notNull(restaurantId, "Restaurant ID must not be null");
        Assert.notNull(orderLines, "Order Lines must not be null");
        Assert.notNull(deliveryInfo, "Delivery Info must not be null");
    }
}
