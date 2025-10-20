package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishType;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.FoodTag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enums")
public class EnumController {

    @GetMapping("/food-tags")
    public ResponseEntity<FoodTag[]> getFoodTags() {
        return ResponseEntity.ok(FoodTag.values());
    }

    @GetMapping("/dish-types")
    public ResponseEntity<DishType[]> getDishTypes() {
        return ResponseEntity.ok(DishType.values());
    }
}
