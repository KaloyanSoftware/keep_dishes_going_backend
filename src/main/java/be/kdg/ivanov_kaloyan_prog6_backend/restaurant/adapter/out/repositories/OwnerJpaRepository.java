package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.repositories;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.entities.OwnerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OwnerJpaRepository extends JpaRepository<OwnerJpaEntity, UUID> {
}
