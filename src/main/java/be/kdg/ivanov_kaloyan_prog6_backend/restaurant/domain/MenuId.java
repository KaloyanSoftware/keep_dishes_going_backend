package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import java.util.UUID;

public record MenuId(UUID id) {

    public static MenuId create(){
        return new MenuId(UUID.randomUUID());
    }

    public static MenuId of(UUID id){
        return new MenuId(id);
    }
}
