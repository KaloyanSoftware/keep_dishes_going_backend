package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands;

import org.springframework.util.Assert;

import java.util.UUID;

public record DeleteDraftCommand(UUID draftId){

    public DeleteDraftCommand(String draftId) {
        this(UUID.fromString(draftId));

        Assert.notNull(draftId, "DraftId must not be null!");
    }
}
