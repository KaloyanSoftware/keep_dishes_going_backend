package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.response;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Item;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemDTO(UUID dishId,
                      UUID restaurantId,
                      String name,
                      BigDecimal price,
                      Integer quantity,
                      String pictureURL) {

    public static ItemDTO from(final Item item){
        return new ItemDTO(item.dishId(), item.restaurantId(), item.name(),
                item.price(), item.quantity(), item.pictureURL());
    }
}
