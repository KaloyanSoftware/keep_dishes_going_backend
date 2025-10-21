package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.entities.DishProjectionJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.repositories.DishProjectionRepository;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.DishProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.DeleteDishProjectionPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.UpdateDishProjectionPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class DishProjectionAdapter implements UpdateDishProjectionPort, DeleteDishProjectionPort {

    private static final Logger log = LoggerFactory.getLogger(DishProjectionAdapter.class);
    private final DishProjectionRepository dishProjections;



    public DishProjectionAdapter(final DishProjectionRepository dishProjections) {
        this.dishProjections = dishProjections;
    }

    @Override
    public void update(DishProjection projection) {
        log.error("Saving dish projection with id : {}", projection.getId());

        final DishProjectionJpaEntity entity = new DishProjectionJpaEntity(
                projection.getId(), projection.getRestaurantId(), projection.getStockStatus(),
                projection.getName(), projection.getType(), projection.getDescription(),
                projection.getPrice().doubleValue(), projection.getPictureURL(), projection.getTags());

        dishProjections.save(entity);
    }

    @Override
    public void delete(UUID id) {
        dishProjections.deleteById(id);
    }
}
