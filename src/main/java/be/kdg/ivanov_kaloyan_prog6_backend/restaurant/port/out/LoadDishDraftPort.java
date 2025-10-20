package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishDraft;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadDishDraftPort {

    Optional<DishDraft> loadBy(UUID id);

    List<DishDraft> loadByRestaurant(UUID id);
}
