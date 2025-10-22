package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands;

import org.springframework.util.Assert;

import java.util.UUID;

public record GetAllDishesForRestaurantCommand(UUID restaurantId) {
    public GetAllDishesForRestaurantCommand {
        Assert.notNull(restaurantId, "Restaurant id must not be null");
    }
}
