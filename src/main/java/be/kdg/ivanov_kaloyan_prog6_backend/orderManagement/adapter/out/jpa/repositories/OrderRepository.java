package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.repositories;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.entities.OrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderJpaEntity, UUID> {
}
