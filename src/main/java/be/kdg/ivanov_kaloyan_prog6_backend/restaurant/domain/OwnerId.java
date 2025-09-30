package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import java.util.UUID;

public record OwnerId(UUID uuid) {
    public static OwnerId of(UUID uuid) {
        return new OwnerId(uuid);
    }
}
