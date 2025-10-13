package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands;

import org.springframework.util.Assert;

import java.util.UUID;

public record ItemStockStatusChangedProjectionCommand(UUID restaurantId, UUID dishId,
                                                      boolean outOfStock) {

    public ItemStockStatusChangedProjectionCommand {
        Assert.notNull(restaurantId, "restaurantId must not be null");
        Assert.notNull(dishId, "dishId must not be null");
    }
}
