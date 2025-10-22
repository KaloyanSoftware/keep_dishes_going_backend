package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.embeddables.LocationEmbeddable;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.entities.RestaurantProjectionJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.repositories.RestaurantProjectionJpaRespository;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.RestaurantProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.UpdateRestaurantProjectionPort;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantProjectionJpaAdapter implements UpdateRestaurantProjectionPort {

    private final RestaurantProjectionJpaRespository restaurantProjections;

    public RestaurantProjectionJpaAdapter(final RestaurantProjectionJpaRespository restaurantProjectionJpaRespository) {
        this.restaurantProjections = restaurantProjectionJpaRespository;
    }

    @Override
    public void update(RestaurantProjection restaurant) {
        final LocationEmbeddable location = new LocationEmbeddable(
                restaurant.getLocation().street(), restaurant.getLocation().number(),
                restaurant.getLocation().postalCode(), restaurant.getLocation().city(),
                restaurant.getLocation().country());

        final RestaurantProjectionJpaEntity jpa = new RestaurantProjectionJpaEntity(restaurant.getRestaurantId(), restaurant.getEmail(),
                restaurant.getPictureURL(), restaurant.getDefaultPrepTime(), restaurant.getCuisine() ,location);

        restaurantProjections.save(jpa);
    }
}
