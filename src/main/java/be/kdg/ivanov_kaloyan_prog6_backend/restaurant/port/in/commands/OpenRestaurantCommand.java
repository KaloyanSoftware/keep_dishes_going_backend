package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands;

import org.springframework.util.Assert;

import java.util.UUID;

public record OpenRestaurantCommand(UUID restaurantId) {

    public OpenRestaurantCommand {
        Assert.notNull(restaurantId, "restaurant id must not be null");
    }
}
