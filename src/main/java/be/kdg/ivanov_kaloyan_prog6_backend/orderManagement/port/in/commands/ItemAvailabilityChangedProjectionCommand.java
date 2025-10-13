package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands;

import org.springframework.util.Assert;

import java.util.UUID;

public record ItemAvailabilityChangedProjectionCommand(UUID dishId, UUID restaurantId,
                                                       boolean published, boolean inStock) {

    public ItemAvailabilityChangedProjectionCommand{
        Assert.notNull(dishId, "dishId cannot be null");
        Assert.notNull(restaurantId, "restaurantId cannot be null");
    }
}
