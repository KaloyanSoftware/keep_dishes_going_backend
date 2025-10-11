package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.request;

import java.util.UUID;

public record AddItemToBasketRequest(UUID restaurantId,
                                     UUID dishId,
                                     UUID ownerId) {
}
