package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class Menu {
    private Map<UUID, Dish> dishes = new HashMap<>();

    public void markDishOutOfStock(UUID id){
        //implement custom exception
        Dish dish = Optional.ofNullable(dishes.get(id))
                .orElseThrow();
        dish.outOfStock();
    }

    public Dish addDish(Dish dish){
        return this.dishes.put(dish.getDishId().id(), dish);
    }
}
