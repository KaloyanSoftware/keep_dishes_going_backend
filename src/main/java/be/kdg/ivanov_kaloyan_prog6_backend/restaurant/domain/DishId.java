package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import java.util.UUID;

public record DishId(UUID id) {

    public static DishId create(){
        return new DishId(UUID.randomUUID());
    }

    public static DishId of(UUID id){
        return new DishId(id);
    }
}
