package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands;

import org.springframework.util.Assert;

import java.util.UUID;

public record GetDishesForRestaurantCommand(UUID restaurantId) {
    public GetDishesForRestaurantCommand {
        Assert.notNull(restaurantId, "restaurant id must not be null");
    }
}
