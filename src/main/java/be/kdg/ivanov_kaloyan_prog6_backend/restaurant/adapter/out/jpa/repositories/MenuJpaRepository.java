package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.repositories;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.MenuJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface MenuJpaRepository extends JpaRepository<MenuJpaEntity, UUID> {
    Optional<MenuJpaEntity> getByRestaurantId(UUID restaurantId);
}
