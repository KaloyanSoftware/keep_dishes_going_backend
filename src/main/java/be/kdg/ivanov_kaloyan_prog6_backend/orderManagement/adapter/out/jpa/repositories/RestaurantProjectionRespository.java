package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.repositories;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.entities.RestaurantProjectionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RestaurantProjectionRespository extends JpaRepository<RestaurantProjectionJpaEntity, UUID> {
}
