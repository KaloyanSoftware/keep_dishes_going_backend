package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto.RestaurantDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.mappers.AddressMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.mappers.OpeningHoursMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.request.CreateRestaurantRequest;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Restaurant;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.CreateRestaurantCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.GetRestaurantForOwnerCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.CreateRestaurantUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.GetRestaurantForOwnerUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@PreAuthorize("hasAuthority('owner')")
@RequestMapping("/api")
public class RestaurantController {

    private final CreateRestaurantUseCase  createRestaurantUseCase;

    private final GetRestaurantForOwnerUseCase getRestaurantForOwnerUseCase;

    private final AddressMapper addressMapper;

    private final OpeningHoursMapper openingHoursMapper;


    public RestaurantController(final CreateRestaurantUseCase createRestaurantUseCase,
                                final GetRestaurantForOwnerUseCase getRestaurantForOwnerUseCase,
                                final AddressMapper addressMapper,
                                final OpeningHoursMapper openingHoursMapper) {
        this.createRestaurantUseCase = createRestaurantUseCase;
        this.getRestaurantForOwnerUseCase = getRestaurantForOwnerUseCase;
        this.addressMapper = addressMapper;
        this.openingHoursMapper = openingHoursMapper;
    }

    @PostMapping("/restaurants")
    public ResponseEntity<RestaurantDTO> post(@RequestBody CreateRestaurantRequest request) {

        final CreateRestaurantCommand command = new CreateRestaurantCommand(
                request.ownerId(),
                addressMapper.toDomain(request.address()),
                request.email(),
                request.pictureURL(),
                request.defaultPrepTime(),
                request.cuisineType(),
                openingHoursMapper.toDomain(request.openingHours()));

        return ResponseEntity.status(HttpStatus.CREATED).body(RestaurantDTO.from(this.createRestaurantUseCase.createRestaurant(command)));
    }

    @GetMapping("/owners/{ownerId}/restaurant")
    public ResponseEntity<RestaurantDTO> get(@PathVariable String ownerId) {
        final var command = new GetRestaurantForOwnerCommand(UUID.fromString(ownerId));

        final Restaurant restaurant = getRestaurantForOwnerUseCase.getRestaurant(command);

        if (restaurant == null) {
            // Owner doesn’t have a restaurant yet
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(RestaurantDTO.from(restaurant));
    }
}
