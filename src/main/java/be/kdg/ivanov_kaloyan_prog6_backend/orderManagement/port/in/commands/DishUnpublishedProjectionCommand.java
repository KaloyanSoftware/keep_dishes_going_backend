package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands;

import org.springframework.util.Assert;

import java.util.UUID;

public record DishUnpublishedProjectionCommand(UUID dishId){
    public DishUnpublishedProjectionCommand {
        Assert.notNull(dishId, "dishId must not be null");
    }
}
