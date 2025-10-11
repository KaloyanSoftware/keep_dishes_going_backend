package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.PublishedItemSnapshot;
import java.util.Optional;
import java.util.UUID;

public interface PublishedDishCatalog {
    Optional<PublishedItemSnapshot> findPublishedDish(UUID restaurantId, UUID dishId);
}
