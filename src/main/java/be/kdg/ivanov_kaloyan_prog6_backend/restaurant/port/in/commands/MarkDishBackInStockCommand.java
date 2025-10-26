package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands;

import org.springframework.util.Assert;
import java.util.UUID;

public record MarkDishBackInStockCommand(UUID dishId, UUID restaurantId) {
    public MarkDishBackInStockCommand {
        Assert.notNull(dishId, "dishId must not be null");
        Assert.notNull(restaurantId, "restaurantId must not be null");
    }
}
