package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands;

import org.springframework.util.Assert;
import java.util.UUID;

public record PublishDishDraftCommand(UUID draftId,
                                      UUID restaurantId) {

    public PublishDishDraftCommand {
        Assert.notNull(draftId, "draftId must not be null");
        Assert.notNull(restaurantId, "restaurantId must not be null");
    }
}
