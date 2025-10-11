package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands;

import org.springframework.util.Assert;

import java.util.UUID;

public record RemoveBasketItemCommand(UUID basketId,
                                      UUID dishId) {
    public RemoveBasketItemCommand {
        Assert.notNull(basketId, "basketId cannot be null");
        Assert.notNull(dishId, "dishId cannot be null");
    }
}
