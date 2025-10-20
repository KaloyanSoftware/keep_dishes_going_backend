package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands;


import org.springframework.util.Assert;

import java.util.UUID;

public record GetRestaurantForOwnerCommand(UUID ownerId) {

    public GetRestaurantForOwnerCommand {
        Assert.notNull(ownerId, "ownerId must not be null");
    }
}
