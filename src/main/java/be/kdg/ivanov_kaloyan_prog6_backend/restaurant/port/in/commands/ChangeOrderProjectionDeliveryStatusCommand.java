package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands;

import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.UUID;

public record ChangeOrderProjectionDeliveryStatusCommand(UUID eventId, LocalDateTime occurredAt, UUID restaurantId, UUID orderId, String status){
    public ChangeOrderProjectionDeliveryStatusCommand {
        Assert.notNull(eventId, "eventId must not be null");
        Assert.notNull(occurredAt, "occurredAt must not be null");
        Assert.notNull(restaurantId, "restaurantId must not be null");
        Assert.notNull(orderId, "orderId must not be null");
        Assert.notNull(status, "status must not be null");
    }
}

