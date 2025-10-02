package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.repositories;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.entities.RestaurantJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RestaurantJpaRepository extends JpaRepository<RestaurantJpaEntity, UUID> {
}
