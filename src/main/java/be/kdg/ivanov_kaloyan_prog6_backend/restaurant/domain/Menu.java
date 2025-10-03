package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Menu {

    private final Map<UUID, Dish> dishes = new HashMap<>();


    public Dish addDish(DishSnapshot draft){
        Dish dish = new Dish(draft, null);
        dishes.put(dish.dishId().id(), dish);
        return dish;
    }
}
