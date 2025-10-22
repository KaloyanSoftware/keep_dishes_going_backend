package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.repositories;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.entities.DishProjectionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DishProjectionJpaRepository extends JpaRepository<DishProjectionJpaEntity, UUID> {
    List<DishProjectionJpaEntity> findAllByRestaurantId(UUID restaurantId);
}
