package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.entities.ItemAvailabilityProjectionJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.repositories.ItemAvailabilityJpaRepository;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.ItemAvailability;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.LoadItemAvailabilityPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.UpdateItemAvailabilityPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ItemAvailabilityProjectionJpaAdapter implements UpdateItemAvailabilityPort, LoadItemAvailabilityPort {

    private final ItemAvailabilityJpaRepository repository;

    public ItemAvailabilityProjectionJpaAdapter(ItemAvailabilityJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public ItemAvailability update(ItemAvailability itemAvailability) {
        final ItemAvailabilityProjectionJpaEntity entity = new ItemAvailabilityProjectionJpaEntity(
                itemAvailability.getDishId(), itemAvailability.getRestaurantId(),
                itemAvailability.isPublished(), itemAvailability.isInStock()
        );

        final ItemAvailabilityProjectionJpaEntity saved = repository.save(entity);

        return new ItemAvailability(saved.getRestaurantId(), saved.getDishId(),
                saved.isPublished(), saved.isInStock());
    }

    @Override
    public Optional<ItemAvailability> loadBy(UUID dishId) {
        return repository.findById(dishId).map(jpa -> new  ItemAvailability(
                jpa.getRestaurantId(), jpa.getDishId(), jpa.isPublished(), jpa.isInStock()));
    }
}
