package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.mappers.RestaurantProjectionMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.repositories.RestaurantProjectionJpaRespository;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.RestaurantProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.LoadRestaurantProjectionPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.UpdateRestaurantProjectionPort;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class RestaurantProjectionJpaAdapter implements UpdateRestaurantProjectionPort, LoadRestaurantProjectionPort {

    private final RestaurantProjectionJpaRespository restaurantProjections;

    private final RestaurantProjectionMapper mapper;

    public RestaurantProjectionJpaAdapter(final RestaurantProjectionJpaRespository restaurantProjectionJpaRespository,
                                          final RestaurantProjectionMapper mapper) {
        this.restaurantProjections = restaurantProjectionJpaRespository;
        this.mapper = mapper;
    }

    @Override
    public void update(RestaurantProjection restaurant) {
        restaurantProjections.save(mapper.toJpa(restaurant));
    }

    @Override
    public List<RestaurantProjection> loadAll() {
        return restaurantProjections.findAll().stream().map(mapper::toDomain).toList();
    }
}
