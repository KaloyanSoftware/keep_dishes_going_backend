package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.dto.RestaurantProjectionDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases.GetAlRestaurantProjectionsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/customer/restaurants")
public class RestaurantProjectionController {

    private final GetAlRestaurantProjectionsUseCase getAlRestaurantProjectionsUseCase;

    public RestaurantProjectionController(final GetAlRestaurantProjectionsUseCase getAlRestaurantProjectionsUseCase) {
        this.getAlRestaurantProjectionsUseCase = getAlRestaurantProjectionsUseCase;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantProjectionDTO>> getAllRestaurants() {
        return ResponseEntity.ok().body(
                getAlRestaurantProjectionsUseCase.getAll().stream().map(RestaurantProjectionDTO::from).toList());
    }
}
