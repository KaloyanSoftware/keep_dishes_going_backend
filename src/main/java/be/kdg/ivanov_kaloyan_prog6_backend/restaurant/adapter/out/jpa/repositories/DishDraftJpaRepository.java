package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.repositories;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.DishDraftJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface DishDraftJpaRepository extends JpaRepository<DishDraftJpaEntity, UUID> {
    List<DishDraftJpaEntity> getAllByRestaurantId(UUID restaurantId);
}
