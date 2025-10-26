package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands;

import org.springframework.util.Assert;

import java.util.UUID;

public record DishStockStatusChangedProjectionCommand(UUID dishId, String stockStatus) {
    public DishStockStatusChangedProjectionCommand {
        Assert.notNull(dishId, "dishId must not be null");
        Assert.notNull(stockStatus, "stockStatus must not be null");
    }
}
