package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//aggregate
public class Menu {

    private MenuId id;

    private RestaurantId restaurantId;

    private final Map<UUID, Dish> dishes = new HashMap<>();

    private static final int MAX_PUBLISHED_DISHES = 10;

    public Dish publishDish(UUID dishId){
        Dish dish = dishes.get(dishId);

        if(dish == null || dish.getVisibility() != Dish.Visibility.UNPUBLISHED) {
            throw new InvalidPublishDishException("Dish is already published, out of stock or doesn't exist!");
        }else{
            if(maxCapacityReached()){
                throw new MenuCapacityException("Can't make more dishes available, max capacity reached: 10!");
            }else{
                dish.publish();
                dishes.put(dishId, dish);
            }
        }
        return dish;
    }

    public Dish unpublishDish(UUID dishId){
        Dish dish = dishes.get(dishId);

        if(dish == null || dish.getVisibility() != Dish.Visibility.PUBLISHED) {
            throw new InvalidPublishDishException("Dish is already unpublished, out of stock or doesn't exist!");
        }else{
            dish.unpublish();
            dishes.put(dishId, dish);
        }
        return dish;
    }

    public Dish publishDraft(DishDraft draft){

        if(draft.getDishId() != null){
            Dish dish = dishes.get(draft.getDishId().id());

            if(dish == null || dish.getVisibility() != Dish.Visibility.UNPUBLISHED){
                throw new InvalidPublishDraftException("""
                        The dish for this draft is still on the live menu or out of stock!
                        Remove the dish from the live menu or re-stock to publish this draft!
                        """);
            }else{
                dishes.remove(draft.getDishId().id());
            }
        }

        Dish dish = new Dish(draft, this.id);

        if(maxCapacityReached()){
            throw new MenuCapacityException("Can't publish more dishes, max capacity reached!");
        }else{
            dish.publish();
            dishes.put(dish.getId().id(), dish);
        }

        return dish;
    }

    public Dish markDishOutOfStock(UUID dishId){
        Dish dish = dishes.get(dishId);

        if(dish == null || dish.getVisibility() == Dish.Visibility.OUT_OF_STOCK) {
            throw new InvalidMarkDishOutOfStockException("Dish is already out of stock or doesn't exist!");
        }else{
            dish.markOutOfStock();
            dishes.put(dishId, dish);
        }
        return dish;
    }

    public Dish markDishBackInStock(UUID dishId){
        Dish dish = dishes.get(dishId);

        if(dish == null || dish.getVisibility() != Dish.Visibility.OUT_OF_STOCK) {
            throw new InvalidMarkDishBackInStockException("Dish is already in stock or doesn't exist!");
        }else{
            if(maxCapacityReached()){
                throw new MenuCapacityException("""
                        Can't make more dishes available, max capacity reached: 10!
                        Take a dish off the live menu to make this one available!
                        """);
            }else{
                dish.publish();
                dishes.put(dishId, dish);
            }
        }
        return dish;
    }



    public static Menu rehydrate(MenuId id, RestaurantId restaurantId, Map<UUID, Dish> dishes) {
        Menu m = new Menu(restaurantId);
        m.id = id;
        m.dishes.clear();
        if (dishes != null) m.dishes.putAll(dishes);
        return m;
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
        List<Dish> availableDishes = dishes.values().stream().
                filter(dish -> dish.getVisibility() != Dish.Visibility.PUBLISHED).toList();

        return MAX_PUBLISHED_DISHES == availableDishes.size();
    }

    public Map<UUID, Dish> getDishes() {
        return dishes;
    }
}
