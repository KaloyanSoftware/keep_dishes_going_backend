package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import be.kdg.ivanov_kaloyan_prog6_backend.common.events.*;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions.*;
import java.util.*;
import java.util.stream.Stream;

public class Menu {

    private MenuId id;

    private RestaurantId restaurantId;

    private final Map<UUID, Dish> dishes = new HashMap<>();

    private List<DomainEvent> eventStore = new ArrayList<>();

    private List<DomainEvent> uncommittedEvents = new ArrayList<>();

    private static final int MAX_PUBLISHED_DISHES = 10;

    public static Menu create(UUID restaurantId) {
        Menu menu = new Menu(MenuId.create(), RestaurantId.of(restaurantId));
        return menu;
    }

    public static Menu rehydrate(MenuId id, RestaurantId restaurantId, Map<UUID, Dish> dishes, List<DomainEvent> events) {
        Menu m = new Menu(id, restaurantId);
        m.dishes.clear();
        if (dishes != null) m.dishes.putAll(dishes);
        if (events != null) m.eventStore.addAll(events);
        return m;
    }

    public Menu(MenuId menuId, RestaurantId restaurantId) {
        this.id = menuId;
        this.restaurantId = restaurantId;
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

        final List<String> tags = dish.getTags().stream()
                .map(FoodTag::name).toList();

        this.uncommittedEvents.add(new DishPublishedEvent(dishId, restaurantId.id(),dish.getStockStatus().name(), dish.getName(),
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

        this.uncommittedEvents.add(new DishUnpublishedEvent(dishId));
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

        final List<String> tags = dish.getTags().stream()
                .map(FoodTag::name).toList();

        this.uncommittedEvents.add(new DishPublishedEvent(dish.getId().id(), restaurantId.id(),dish.getStockStatus().name(), dish.getName(),
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

        this.uncommittedEvents.add(new DishOutOfStockEvent(dishId, dish.published()));
        return dish;
    }

    public Dish markDishBackInStock(UUID dishId){
        Dish dish = dishes.get(dishId);

        if(dish == null || !dish.outOfStock()) {
            throw new InvalidMarkDishBackInStockException("Dish is already in stock or doesn't exist!");
        }

        if (publishedCapacityReached()) {
            throw new MenuCapacityException("""
            Can't make more dishes available, max capacity reached: %d!
            Take a dish off the live menu to make this one available!
            """.formatted(MAX_PUBLISHED_DISHES));
        }

        dish.markInStock();
        dishes.put(dishId, dish);

        this.uncommittedEvents.add(new DishBackInStockEvent(dishId, dish.published()));
        return dish;
    }

    public void deleteDish(UUID dishId){
        final Dish dish = dishes.get(dishId);

        if(dish.published()){
            throw new IllegalArgumentException("Cannot delete dishes that are on the live menu!");
        }else{
            dishes.remove(dishId);
        }
    }

    public int getPublishedCount() {
        return eventStore.stream()
                .mapToInt(event -> switch (event) {
                    case DishPublishedEvent ignored -> 1;
                    case DishUnpublishedEvent ignored -> -1;
                    default -> 0;
                })
                .sum();
    }

    public List<DomainEvent> getUncommittedEvents() {
        return new ArrayList<>(uncommittedEvents);
    }

    public void commitEvents(){
        this.eventStore.addAll(uncommittedEvents);
        uncommittedEvents.clear();
    }

    public List<DomainEvent> getDomainEvents() {
        return new ArrayList<>(Stream.concat(eventStore.stream(), uncommittedEvents.stream()).toList());
    }

    private boolean publishedCapacityReached(){
        return getPublishedCount() == MAX_PUBLISHED_DISHES;
    }

    public MenuId getId() {
        return id;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public Map<UUID, Dish> getDishes() {
        return dishes;
    }
}
