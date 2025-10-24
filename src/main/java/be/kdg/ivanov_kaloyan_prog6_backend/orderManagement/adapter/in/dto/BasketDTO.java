package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.dto;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Basket;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public record BasketDTO(UUID basketId, Map<UUID, ItemDTO> items) {

    public static BasketDTO from(final Basket basket) {
        Map<UUID, ItemDTO> itemDTOs = basket.getBasketLines().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> ItemDTO.from(entry.getValue())
                ));

        return new BasketDTO(basket.getId().id(),itemDTOs);
    }
}
