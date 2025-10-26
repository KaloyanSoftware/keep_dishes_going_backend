package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.entities.DishProjectionJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.mappers.DishProjectionMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.repositories.DishProjectionJpaRepository;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.DishProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.DeleteDishProjectionPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.LoadDishProjectionPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.UpdateDishProjectionPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class DishProjectionAdapter implements UpdateDishProjectionPort, DeleteDishProjectionPort, LoadDishProjectionPort {

    private static final Logger log = LoggerFactory.getLogger(DishProjectionAdapter.class);

    private final DishProjectionJpaRepository dishProjections;

    private final DishProjectionMapper mapper;

    public DishProjectionAdapter(final DishProjectionJpaRepository dishProjections,
                                 final DishProjectionMapper mapper) {
        this.dishProjections = dishProjections;
        this.mapper = mapper;
    }

    @Override
    public void update(DishProjection projection) {
        log.error("Saving dish projection with id : {}", projection.getId());
        dishProjections.save(mapper.toJpa(projection));
    }

    @Override
    public void delete(UUID id) {
        dishProjections.deleteById(id);
    }

    @Override
    public List<DishProjection> loadAllBy(UUID restaurantId) {
        return dishProjections.findAllByRestaurantId(restaurantId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<DishProjection> loadBy(UUID dishProjectionId) {
        return this.dishProjections.findById(dishProjectionId).map(mapper::toDomain);
    }
}
