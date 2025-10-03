package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.DishDraftJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.RestaurantJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers.DishDraftMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.repositories.DishDraftJpaRepository;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.repositories.RestaurantJpaRepository;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishDraft;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.SaveDishDraftPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("jpa")
@Profile("jpa")
public class DishDraftJpaAdapter implements SaveDishDraftPort {

    private final DishDraftJpaRepository dishDraftJpaRepository;

    private final RestaurantJpaRepository restaurantJpaRepository;

    private final DishDraftMapper dishDraftMapper;

    public DishDraftJpaAdapter(final DishDraftJpaRepository dishDraftJpaRepository,
                               final DishDraftMapper dishDraftMapper,
                               final RestaurantJpaRepository restaurantJpaRepository) {
        this.dishDraftJpaRepository = dishDraftJpaRepository;
        this.dishDraftMapper = dishDraftMapper;
        this.restaurantJpaRepository = restaurantJpaRepository;
    }

    @Override
    public DishDraft save(DishDraft draft) {
        DishDraftJpaEntity dishDraftJpaEntity = dishDraftMapper.toJpa(draft);

        RestaurantJpaEntity restaurantJpaEntity = restaurantJpaRepository.getReferenceById(draft.getRestaurantId().id());

        dishDraftJpaEntity.setRestaurant(restaurantJpaEntity);

        DishDraftJpaEntity savedEntity = dishDraftJpaRepository.save(dishDraftJpaEntity);

        return dishDraftMapper.toDomain(savedEntity);
    }
}
