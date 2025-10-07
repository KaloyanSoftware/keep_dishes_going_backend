package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.request;

import java.util.UUID;

public record PublishDishDraftRequest(UUID restaurantId,
                                      UUID draftId) {
}
