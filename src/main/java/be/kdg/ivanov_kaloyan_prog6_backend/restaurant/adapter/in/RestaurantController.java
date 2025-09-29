package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.request.CreateRestaurantRequest;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.CreateRestaurantCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.CreateRestaurantUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final CreateRestaurantUseCase  createRestaurantUseCase;

    public RestaurantController(final CreateRestaurantUseCase createRestaurantUseCase) {
        this.createRestaurantUseCase = createRestaurantUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateRestaurantRequest request) {
        final CreateRestaurantCommand command = new CreateRestaurantCommand(
                request.ownerId(),
                request.address(),
                request.email(),
                request.pictureURL(),
                request.defaultPrepTime(),
                request.cuisineType(),
                request.openingHours());

        this.createRestaurantUseCase.createRestaurant(command);

        return ResponseEntity.ok().build();
    }
}
