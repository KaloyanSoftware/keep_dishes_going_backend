package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import java.util.UUID;

public record OrderId(UUID orderId) {
    public static OrderId create(){
        return new OrderId(UUID.randomUUID());
    }

    public static OrderId of(UUID id){
        return new OrderId(id);
    }
}
