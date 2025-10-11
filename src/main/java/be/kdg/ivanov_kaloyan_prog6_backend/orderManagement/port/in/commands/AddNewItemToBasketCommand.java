package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands;

import java.util.UUID;
import org.springframework.util.Assert;

public record AddNewItemToBasketCommand(UUID restaurantId, UUID dishId,
                                         UUID ownerId) {
    public AddNewItemToBasketCommand {
        Assert.notNull(restaurantId, "restaurantId cannot be null");
        Assert.notNull(dishId, "dishId cannot be null");
        Assert.notNull(ownerId, "ownerId cannot be null");
    }
}
