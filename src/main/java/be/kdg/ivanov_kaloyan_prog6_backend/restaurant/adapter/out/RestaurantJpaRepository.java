package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RestaurantJpaRepository extends JpaRepository<RestaurantJpaEntity, UUID> {
}
