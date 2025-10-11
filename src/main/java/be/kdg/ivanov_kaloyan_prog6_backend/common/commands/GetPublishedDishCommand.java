package be.kdg.ivanov_kaloyan_prog6_backend.common.commands;

import org.springframework.util.Assert;

import java.util.UUID;

public record GetPublishedDishCommand(UUID restaurantId,
                                      UUID dishId) {

    public GetPublishedDishCommand {
        Assert.notNull(restaurantId, "Restaurant id can't be null");
        Assert.notNull(dishId, "Dish id can't be null");
    }
}
