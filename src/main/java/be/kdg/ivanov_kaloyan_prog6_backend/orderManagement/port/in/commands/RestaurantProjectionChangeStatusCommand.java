package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands;

import org.springframework.util.Assert;

import java.util.UUID;

public record RestaurantProjectionChangeStatusCommand(UUID restaurantId, boolean isOpen) {

    public RestaurantProjectionChangeStatusCommand {
        Assert.notNull(restaurantId, "Restaurant ID must not be null");
        Assert.notNull(isOpen, "isOpen must not be null");
    }
}
