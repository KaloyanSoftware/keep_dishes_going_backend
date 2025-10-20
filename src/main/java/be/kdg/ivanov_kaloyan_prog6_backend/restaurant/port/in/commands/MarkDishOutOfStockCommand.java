package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands;

import org.springframework.util.Assert;

import java.util.UUID;

public record MarkDishOutOfStockCommand(UUID dishId, UUID menuId) {

    public MarkDishOutOfStockCommand {
        Assert.notNull(dishId, "dishId must not be null");
        Assert.notNull(menuId, "restaurantId must not be null");
    }
}
