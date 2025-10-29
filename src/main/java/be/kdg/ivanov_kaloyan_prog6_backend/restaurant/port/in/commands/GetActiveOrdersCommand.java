package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands;

import org.springframework.util.Assert;
import java.util.UUID;

public record GetActiveOrdersCommand(UUID restaurantId) {
    public GetActiveOrdersCommand {
        Assert.notNull(restaurantId, "restaurantId must not be null");
    }
}
