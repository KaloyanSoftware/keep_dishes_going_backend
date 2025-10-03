package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import java.util.UUID;

public record DishDraftId(UUID id) {

    public static DishDraftId of(UUID id) {
        return new DishDraftId(id);
    }

    public static DishDraftId create() {
        return new DishDraftId(UUID.randomUUID());
    }

}
