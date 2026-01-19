package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands;

import org.springframework.util.Assert;

import java.util.UUID;

public record DeleteDishCommand(UUID restaurantId, UUID dishId) {

    public DeleteDishCommand(String restaurantId, String dishId){
        this(UUID.fromString(restaurantId), UUID.fromString(dishId));

        Assert.notNull(restaurantId, "Restaurant id must not be null!");
        Assert.notNull(dishId, "Dish id must not be null!");
    }
}
