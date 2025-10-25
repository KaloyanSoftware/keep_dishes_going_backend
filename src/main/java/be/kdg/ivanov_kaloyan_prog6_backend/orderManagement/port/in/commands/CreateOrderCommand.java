package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.dto.CustomerInfoDTO;
import org.springframework.util.Assert;
import java.util.UUID;

public record CreateOrderCommand(UUID basketId, CustomerInfoDTO customerInfo, UUID customerSessionId){
    public CreateOrderCommand {
        Assert.notNull(basketId, "basketId must not be null");
        Assert.notNull(customerInfo, "customerInfo must not be null");
        Assert.notNull(customerInfo.name(), "name must not be null");
        Assert.notNull(customerInfo.deliveryAddress(), "delivery address must not be null");
        Assert.notNull(customerInfo.email(), "email must not be null");
        Assert.notNull(customerSessionId, "customerSessionId must not be null");
    }
}
