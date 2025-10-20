package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.entities.DishProjectionJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.repositories.DishProjectionRepository;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.DishProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.UpdateDishProjectionPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class DishSavedProjectionAdapter implements UpdateDishProjectionPort {

    private static final Logger log = LoggerFactory.getLogger(DishSavedProjectionAdapter.class);
    private final DishProjectionRepository dishProjections;



    public DishSavedProjectionAdapter(final DishProjectionRepository dishProjections) {
        this.dishProjections = dishProjections;
    }

    @Override
    public void update(DishProjection projection) {
        log.error("Saving dish projection with id : {}", projection.getId().id());

        final DishProjectionJpaEntity entity = new DishProjectionJpaEntity(
                projection.getId().id(), projection.getRestaurantId().id(), projection.getStockStatus(),
                projection.getName(), projection.getType(), projection.getDescription(),
                projection.getPrice().doubleValue(), projection.getPictureURL(), projection.getTags());

        dishProjections.save(entity);
    }
}
