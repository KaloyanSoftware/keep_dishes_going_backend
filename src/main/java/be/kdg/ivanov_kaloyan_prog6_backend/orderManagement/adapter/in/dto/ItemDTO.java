package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.dto;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.BasketLine;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemDTO(UUID dishId,
                      UUID restaurantId,
                      String name,
                      BigDecimal price,
                      Integer quantity,
                      String pictureURL) {

    public static ItemDTO from(final BasketLine basketLine){
        return new ItemDTO(basketLine.dishId(), basketLine.restaurantId(), basketLine.name(),
                basketLine.price(), basketLine.quantity(), basketLine.pictureURL());
    }
}
