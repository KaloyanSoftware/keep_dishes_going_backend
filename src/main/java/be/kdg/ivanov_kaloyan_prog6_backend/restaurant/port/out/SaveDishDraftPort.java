package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishDraft;

public interface SaveDishDraftPort {

    DishDraft save(DishDraft draft);
}
