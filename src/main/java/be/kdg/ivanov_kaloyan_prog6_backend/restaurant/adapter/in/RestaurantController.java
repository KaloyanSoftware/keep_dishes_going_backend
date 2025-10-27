package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto.RestaurantDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.mappers.AddressMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.mappers.OpeningHoursMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.request.CreateRestaurantRequest;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Restaurant;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.CreateRestaurantCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.GetRestaurantForOwnerCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.CloseRestaurantCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.OpenRestaurantCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.CloseRestaurantUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.CreateRestaurantUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.GetRestaurantForOwnerUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.OpenRestaurantUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class RestaurantController {

    private final CreateRestaurantUseCase  createRestaurantUseCase;

    private final GetRestaurantForOwnerUseCase getRestaurantForOwnerUseCase;

    private final CloseRestaurantUseCase closeRestaurantUseCase;

    private final OpenRestaurantUseCase openRestaurantUseCase;

    private final AddressMapper addressMapper;

    private final OpeningHoursMapper openingHoursMapper;


    public RestaurantController(final CreateRestaurantUseCase createRestaurantUseCase,
                                final GetRestaurantForOwnerUseCase getRestaurantForOwnerUseCase,
                                final AddressMapper addressMapper,
                                final OpeningHoursMapper openingHoursMapper,
                                final CloseRestaurantUseCase closeRestaurantUseCase,
                                final OpenRestaurantUseCase openRestaurantUseCase) {
        this.createRestaurantUseCase = createRestaurantUseCase;
        this.getRestaurantForOwnerUseCase = getRestaurantForOwnerUseCase;
        this.addressMapper = addressMapper;
        this.openingHoursMapper = openingHoursMapper;
        this.closeRestaurantUseCase = closeRestaurantUseCase;
        this.openRestaurantUseCase = openRestaurantUseCase;
    }

    @PostMapping("/restaurants")
    @PreAuthorize("hasAuthority('owner')")
    public ResponseEntity<RestaurantDTO> create(@RequestBody CreateRestaurantRequest request) {

        final CreateRestaurantCommand command = new CreateRestaurantCommand(
                UUID.fromString(request.ownerId()),
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

    @PatchMapping("/owners/restaurants/{restaurantId}/closed")
    @PreAuthorize("hasAuthority('owner')")
    public ResponseEntity<RestaurantDTO> close(@PathVariable String restaurantId){

        final CloseRestaurantCommand command = new CloseRestaurantCommand(UUID.fromString(restaurantId));

        return ResponseEntity.ok().body(RestaurantDTO.from(closeRestaurantUseCase.close(command)));
    }

    @PatchMapping("/owners/restaurants/{restaurantId}/opened")
    @PreAuthorize("hasAuthority('owner')")
    public ResponseEntity<RestaurantDTO> open(@PathVariable String restaurantId){

        final OpenRestaurantCommand command = new OpenRestaurantCommand(UUID.fromString(restaurantId));

        return ResponseEntity.ok().body(RestaurantDTO.from(openRestaurantUseCase.open(command)));
    }
}
