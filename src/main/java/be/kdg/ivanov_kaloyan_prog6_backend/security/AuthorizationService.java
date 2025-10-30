package be.kdg.ivanov_kaloyan_prog6_backend.security;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.repositories.RestaurantJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class AuthorizationService {
    private static final Logger log = LoggerFactory.getLogger(AuthorizationService.class);
    private final RestaurantJpaRepository restaurantRepository;


    public AuthorizationService(RestaurantJpaRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public boolean isOwnerOfRestaurant(Authentication auth, UUID restaurantId) {
        log.error("restaurant id: {}", restaurantId);
        JwtAuthenticationToken jwt = ((JwtAuthenticationToken) auth);
        if (jwt == null || restaurantId == null) return false;

        String ownerId = jwt.getToken().getSubject();

        log.error("ownerId: {}", ownerId);

        if (ownerId == null){
            log.error("ownerId is null");

            return false;
        }else{
            UUID ownerUUID = UUID.fromString(ownerId);
            return restaurantRepository.existsByIdAndOwnerId(restaurantId, ownerUUID);
        }
    }
}
