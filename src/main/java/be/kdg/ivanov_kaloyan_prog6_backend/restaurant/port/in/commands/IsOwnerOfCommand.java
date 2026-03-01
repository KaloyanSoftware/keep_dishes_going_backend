package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands;

import org.springframework.util.Assert;

import java.util.UUID;

public record IsOwnerOfCommand(UUID ownerId, UUID restaurantId) {
    public IsOwnerOfCommand {
        Assert.notNull(ownerId, "Owner id must not be mull!");
        Assert.notNull(restaurantId, "Restaurant id must not be mull!");
    }
}
