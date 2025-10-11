package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import java.util.UUID;

public record BasketId(UUID id) {
    public static BasketId create(){
        return new BasketId(UUID.randomUUID());
    }
}
