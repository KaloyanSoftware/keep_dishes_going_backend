package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import be.kdg.ivanov_kaloyan_prog6_backend.common.events.*;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions.*;

import java.math.BigDecimal;
import java.util.*;

//aggregate
public class Menu {

    private MenuId id;

    private RestaurantId restaurantId;

    private final Map<UUID, Dish> dishes = new HashMap<>();

    private List<DomainEvent> events = new ArrayList<>();

    private static final int MAX_PUBLISHED_DISHES = 10;

    private int publishedCount = 0;

    public static Menu create(UUID restaurantId) {
        Menu menu = new Menu(MenuId.create(), RestaurantId.of(restaurantId));
        menu.events.add(new SaveMenuEvent(menu.id.id(), restaurantId));
        return menu;
    }

    public Dish publishDish(UUID dishId){
        Dish dish = dishes.get(dishId);

        if(dish == null || dish.published()) {
            throw new InvalidPublishDishException("Dish is already published or doesn't exist!");
        }

        if(publishedCapacityReached()){
            throw new MenuCapacityException("Can't make more dishes available, max capacity reached: 10!");
        }

        dish.publish();
        dishes.put(dishId, dish);
        publishedCount++;

        final List<String> tags = dish.getTags().stream()
                .map(FoodTag::name).toList();

        this.events.add(new DishPublishedEvent(dishId, restaurantId.id(),dish.getStockStatus().name(), dish.getName(),
                dish.getType().name(), tags, dish.getDescription(), dish.getPrice(), dish.getPictureURL()));
        return dish;
    }

    public Dish unpublishDish(UUID dishId){
        Dish dish = dishes.get(dishId);

        if(dish == null || !dish.published()) {
            throw new InvalidPublishDishException("Dish is already unpublished or doesn't exist!");
        }

        dish.unpublish();
        dishes.put(dishId, dish);
        publishedCount--;

        this.events.add(new DishUnpublishedEvent(dishId, restaurantId.id(), dish.orderable()));
        return dish;
    }

    public Dish publishDraft(DishDraft draft){

        if(draft.getDishId() != null){
            Dish dish = dishes.get(draft.getDishId().id());

            if(dish == null || dish.published()) {
                throw new InvalidPublishDraftException("""
                        The dish for this draft is still on the live menu!
                        Remove the dish from the live menu to publish this draft!
                        """);
            }

            dishes.remove(dish.getId().id());
        }

        Dish dish = new Dish(draft, this.id);

        if(publishedCapacityReached()){
            throw new MenuCapacityException("Can't publish more dishes, max capacity reached!");
        }

        dish.publish();
        dish.markInStock();
        dishes.put(dish.getId().id(), dish);
        publishedCount++;

        final List<String> tags = dish.getTags().stream()
                .map(FoodTag::name).toList();

        this.events.add(new DishPublishedEvent(dish.getId().id(), restaurantId.id(),dish.getStockStatus().name(), dish.getName(),
                dish.getType().name(), tags, dish.getDescription(), dish.getPrice(), dish.getPictureURL()));
        return dish;
    }

    public Dish markDishOutOfStock(UUID dishId){
        Dish dish = dishes.get(dishId);

        if(dish == null || dish.outOfStock()) {
            throw new InvalidMarkDishOutOfStockException("Dish is already out of stock or doesn't exist!");
        }

        dish.markOutOfStock();
        dishes.put(dishId, dish);

        this.events.add(new DishOutOfStockEvent(dishId, restaurantId.id(), dish.orderable()));
        return dish;
    }

    public Dish markDishBackInStock(UUID dishId){
        Dish dish = dishes.get(dishId);

        if(dish == null || !dish.outOfStock()) {
            throw new InvalidMarkDishBackInStockException("Dish is already in stock or doesn't exist!");
        }

        if(publishedCapacityReached()){
            throw new MenuCapacityException("""
                        Can't make more dishes available, max capacity reached: 10!
                        Take a dish off the live menu to make this one available!
                        """);
        }

        dish.markInStock();
        dishes.put(dishId, dish);

        this.events.add(new DishBackInStockEvent(dishId, restaurantId.id(), dish.orderable()));
        return dish;
    }

    public Dish findOrderableDish(UUID dishId){
        Dish dish = dishes.get(dishId);

        if(dish == null){
            throw new DishNotFoundException("Dish with id: " + dishId + " does not exist!");
        }

        if(!dish.orderable()){
            throw new DishNotOrderableException("Dish with id " + dishId + " cannot be ordered because it's unpublished and/or out of stock!");
        }

        return dish;
    }



    public static Menu rehydrate(MenuId id, RestaurantId restaurantId, Map<UUID, Dish> dishes, int publishedCount) {
        Menu m = new Menu(id, restaurantId);
        m.dishes.clear();
        if (dishes != null) m.dishes.putAll(dishes);
        m.publishedCount = publishedCount;
        return m;
    }

    public Menu(MenuId menuId, RestaurantId restaurantId) {
        setId(menuId);
        setRestaurantId(restaurantId);
    }

    public List<DomainEvent> getEvents() {
        return events;
    }

    public MenuId getId() {
        return id;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    private boolean publishedCapacityReached(){
        return publishedCount == MAX_PUBLISHED_DISHES;
    }

    public Map<UUID, Dish> getDishes() {
        return dishes;
    }

    public int getPublishedCount() {
        return publishedCount;
    }

    public void setId(MenuId id) {
        this.id = id;
    }

    public void setRestaurantId(RestaurantId restaurantId) {
        this.restaurantId = restaurantId;
    }
}
