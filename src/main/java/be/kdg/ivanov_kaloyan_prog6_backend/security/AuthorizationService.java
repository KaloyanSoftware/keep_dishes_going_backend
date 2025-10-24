package be.kdg.ivanov_kaloyan_prog6_backend.security;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.repositories.RestaurantJpaRepository;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class AuthorizationService {
    private final RestaurantJpaRepository restaurantRepository;

    public AuthorizationService(RestaurantJpaRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public boolean isOwnerOfRestaurant(Jwt jwt, UUID restaurantId) {
        if (jwt == null || restaurantId == null) return false;

        String ownerId = jwt.getSubject();

        if (ownerId == null){
            return false;
        }else{
            UUID ownerUUID = UUID.fromString(jwt.getSubject());
            return restaurantRepository.existsByIdAndOwnerId(restaurantId, ownerUUID);
        }
    }
}
