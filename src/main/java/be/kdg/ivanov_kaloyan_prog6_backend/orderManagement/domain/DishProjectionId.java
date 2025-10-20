package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import java.util.UUID;

public record DishProjectionId(UUID id) {
    public static DishProjectionId create() {
        return new DishProjectionId(UUID.randomUUID());
    }

    public static DishProjectionId  of(UUID id){

        return new DishProjectionId(id);
    }
}
