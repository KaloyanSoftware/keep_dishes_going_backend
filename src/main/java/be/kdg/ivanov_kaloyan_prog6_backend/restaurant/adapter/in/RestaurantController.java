package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto.AddressDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto.OpeningHoursDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto.RestaurantDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.mappers.AddressMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.mappers.OpeningHoursMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.request.CreateRestaurantRequest;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Restaurant;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.CreateRestaurantCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.CreateRestaurantUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final CreateRestaurantUseCase  createRestaurantUseCase;

    private final AddressMapper addressMapper;

    private final OpeningHoursMapper openingHoursMapper;

    public RestaurantController(final CreateRestaurantUseCase createRestaurantUseCase,
                                final AddressMapper addressMapper,
                                final OpeningHoursMapper openingHoursMapper) {
        this.createRestaurantUseCase = createRestaurantUseCase;
        this.addressMapper = addressMapper;
        this.openingHoursMapper = openingHoursMapper;
    }

    @PostMapping
    public ResponseEntity<RestaurantDTO> post(@RequestBody CreateRestaurantRequest request) {

        final AddressDTO addressDTO = request.address();

        final OpeningHoursDTO openingHoursDTO = request.openingHours();

        final CreateRestaurantCommand command = new CreateRestaurantCommand(
                request.ownerId(),
                addressMapper.toDomain(addressDTO),
                request.email(),
                request.pictureURL(),
                request.defaultPrepTime(),
                request.cuisineType(),
                openingHoursMapper.toDomain(openingHoursDTO));

        final Restaurant restaurant = this.createRestaurantUseCase.createRestaurant(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(RestaurantDTO.from(restaurant));
    }
}
