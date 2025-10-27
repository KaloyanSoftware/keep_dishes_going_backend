package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands;

import org.springframework.util.Assert;
import java.util.UUID;

public record CloseRestaurantCommand(UUID restaurantId) {
    public CloseRestaurantCommand {
        Assert.notNull(restaurantId, "restaurantId can't be null");
    }
}
