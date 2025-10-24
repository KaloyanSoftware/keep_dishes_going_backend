package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import java.util.UUID;

public record BasketLineId(UUID id) {

    public static BasketLineId create(){
        return new BasketLineId(UUID.randomUUID());
    }
}
