package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands;

import org.springframework.util.Assert;
import java.util.UUID;

public record RegisterOwnerCommand(UUID id, String firstName, String lastName, String email){
    public RegisterOwnerCommand {
        Assert.notNull(id, "id must not be null");
        Assert.notNull(firstName, "firstName must not be null");
        Assert.notNull(lastName, "lastName must not be null");
        Assert.notNull(email, "email must not be null");
    }
}
