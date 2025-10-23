package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.dto.DishProjectionDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.DishProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.GetAllDishesForRestaurantCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases.GetAllDishProjectionsForRestaurantUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customer/restaurants/{restaurantId}/menu/dishes")
public class DishProjectionController {

    private final GetAllDishProjectionsForRestaurantUseCase  getAllDishProjectionsForRestaurantUseCase;

    public DishProjectionController(final GetAllDishProjectionsForRestaurantUseCase getAllDishProjectionsForRestaurantUseCase) {
        this.getAllDishProjectionsForRestaurantUseCase = getAllDishProjectionsForRestaurantUseCase;
    }

    @GetMapping
    public ResponseEntity<List<DishProjectionDTO>> getAllDishProjectionsForRestaurant(@PathVariable String restaurantId) {
        final GetAllDishesForRestaurantCommand command = new GetAllDishesForRestaurantCommand(UUID.fromString(restaurantId));

        return ResponseEntity.ok().body(getAllDishProjectionsForRestaurantUseCase
                .getAllForRestaurant(command).stream().map(DishProjectionDTO::from).toList());
    }
}
