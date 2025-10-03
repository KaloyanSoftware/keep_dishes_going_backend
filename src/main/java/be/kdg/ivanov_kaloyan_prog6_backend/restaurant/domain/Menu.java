package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.exceptions.InvalidPublishDishException;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.exceptions.MenuCapacityException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//aggregate
public class Menu {

    private MenuId id;

    private RestaurantId restaurantId;

    private final Map<UUID, Dish> dishes = new HashMap<>();

    private static final int MAX_DISHES = 10;

    public void publishDish(UUID dishId){
        Dish dish = dishes.get(dishId);

        if(dish == null || dish.getVisibility() == Dish.Visibility.PUBLISHED) {
            throw new InvalidPublishDishException("Dish is already published or doesn't exist!");
        }else{
            if(maxCapacityReached()){
                throw new MenuCapacityException("Can't publish more dishes, max capacity reached!");
            }else{
                dishes.put(dishId, dish);
                dish.publish();
            }
        }
    }

    public Menu(RestaurantId restaurantId) {
        this.id = MenuId.create();
        this.restaurantId = restaurantId;
    }

    public MenuId getId() {
        return id;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    private boolean maxCapacityReached(){
        return MAX_DISHES == dishes.size();
    }
}
