package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.MarkDishOutOfStockUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/dishes")
public class DishStockController {

    private final MarkDishOutOfStockUseCase markDishOutOfStockUseCase;

    public DishStockController(final MarkDishOutOfStockUseCase markDishOutOfStockUseCase) {
        this.markDishOutOfStockUseCase = markDishOutOfStockUseCase;
    }

    @PatchMapping("{dishId}/outOfStock")
    public ResponseEntity<Void> out(@PathVariable UUID restaurantId, @PathVariable UUID dishId) {
        this.markDishOutOfStockUseCase.markOutOfStock(restaurantId, dishId);
        return ResponseEntity.ok().build();
    }
}
