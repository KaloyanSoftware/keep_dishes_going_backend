package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.dto;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Basket;
import java.util.List;

public record BasketDTO(String basketId, String restaurantId,List<BasketLineDTO> basketLines) {

    public static BasketDTO from(final Basket basket) {
        List<BasketLineDTO> lineDTOs = basket.getBasketLines().values().stream()
                .map(BasketLineDTO::from)
                .toList();

        return new BasketDTO(basket.getId().id().toString(), basket.getRestaurantId().toString(),lineDTOs);
    }
}

