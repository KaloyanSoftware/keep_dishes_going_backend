package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.request;

import java.util.UUID;

public record PublishDishRequest(UUID menuId,
                                 UUID dishId) {
}
