package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands;

import org.springframework.util.Assert;

import java.util.UUID;

public record ChangeOrderProjectionStatusCommand(UUID orderId, String status) {
    public ChangeOrderProjectionStatusCommand {
        Assert.notNull(orderId, "orderId must not be null");
        Assert.notNull(status, "status must not be null");
    }
}
