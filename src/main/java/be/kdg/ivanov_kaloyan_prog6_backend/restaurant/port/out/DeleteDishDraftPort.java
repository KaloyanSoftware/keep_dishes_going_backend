package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out;

import java.util.UUID;

public interface DeleteDishDraftPort {
    void delete(UUID id);
}
