package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Order;
import org.springframework.util.Assert;
import java.util.UUID;

public record ChangeOrderStatusCommand(UUID orderId, Order.OrderStatus status){
    public ChangeOrderStatusCommand {
        Assert.notNull(orderId, "orderId must not be null");
        Assert.notNull(status, "status must not be null");
    }
}
