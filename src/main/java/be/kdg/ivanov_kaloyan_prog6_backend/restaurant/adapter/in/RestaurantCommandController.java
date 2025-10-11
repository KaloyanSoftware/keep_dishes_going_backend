package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.common.commands.GetPublishedDishCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.common.responses.PublishedDishResponse;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Dish;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.DishQueryUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/commands/restaurant")
public class RestaurantCommandController {

    private final DishQueryUseCase dishQueryUseCase;

    public RestaurantCommandController(final DishQueryUseCase dishQueryUseCase) {
        this.dishQueryUseCase = dishQueryUseCase;
    }

    @PostMapping("/get-published-dish")
    public ResponseEntity<PublishedDishResponse> getPublishedDish(
            @RequestBody GetPublishedDishCommand command) {

        final Dish dish = dishQueryUseCase.findPublishedDish(command.restaurantId(), command.dishId());

        var dishResponse = new PublishedDishResponse(command.dishId(), command.restaurantId(),
                dish.getName(), dish.getPrice(), dish.getPictureURL(), dish.outOfStock());

        return ResponseEntity.ok(dishResponse);
    }
}
