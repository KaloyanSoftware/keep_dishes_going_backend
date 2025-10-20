package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import java.util.UUID;

public record MenuProjectionId(UUID id) {
    public MenuProjectionId create(){
        return new MenuProjectionId(UUID.randomUUID());
    }
}
