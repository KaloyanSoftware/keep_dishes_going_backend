package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OwnerJpaRepository extends JpaRepository<OwnerJpaEntity, UUID> {
}
