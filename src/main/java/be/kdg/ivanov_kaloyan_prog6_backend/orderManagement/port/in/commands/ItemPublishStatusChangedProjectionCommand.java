package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands;

import org.springframework.util.Assert;

import java.util.UUID;

public record ItemPublishStatusChangedProjectionCommand(UUID restaurantId, UUID dishId,
                                                        boolean published) {

    public ItemPublishStatusChangedProjectionCommand {
        Assert.notNull(restaurantId, "restaurantId cannot be null");
        Assert.notNull(dishId, "dishId cannot be null");
    }
}
