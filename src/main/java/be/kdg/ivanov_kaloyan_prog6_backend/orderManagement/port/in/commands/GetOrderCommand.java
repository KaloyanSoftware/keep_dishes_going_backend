package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands;

import org.springframework.util.Assert;

import java.util.UUID;

public record GetOrderCommand(UUID orderId) {
    public GetOrderCommand {
        Assert.notNull(orderId, "orderId is null");
    }
}
