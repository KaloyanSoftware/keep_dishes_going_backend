package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import java.util.UUID;

public record OwnerId(UUID id) {
    public static OwnerId of(UUID uuid) {
        return new OwnerId(uuid);
    }

    public static OwnerId create(){
        return new OwnerId(UUID.randomUUID());
    }

    @Override
    public UUID id() {
        return id;
    }
}
