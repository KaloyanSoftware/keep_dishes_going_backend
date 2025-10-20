package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.InvalidBasketItemException;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.ItemNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class Basket {
    private BasketId id;

    private UUID restaurantId;

    private UUID customerSessionId;

    private Map<UUID, Item> items = new HashMap<>();

    public Basket(UUID restaurantId, UUID customerSessionId) {
        this.id = BasketId.create();
        this.restaurantId = restaurantId;
        this.customerSessionId = customerSessionId;
    }

    public void addItem(Item newItem) {
        if(!sameRestaurantCheck(newItem)){
            throw new InvalidBasketItemException("You're not allowed to add dishes from different restaurants to your basket!");
        }

        items.merge(newItem.dishId(), newItem, (existing, incoming) ->
                new Item(existing.restaurantId(),existing.dishId(), existing.name(),
                        existing.price(), existing.quantity() + incoming.quantity(),
                        existing.pictureURL()));

    }

    public void removeItem(UUID dishId) {

        Item item = items.get(dishId);

        if(item == null){
            throw new ItemNotFoundException("Item with id: " + dishId + " does not exist!");
        }

        if(item.quantity() - 1 == 0 ){
            items.remove(dishId);
        }else{
            items.put(item.dishId(), new Item(item.restaurantId(),item.dishId(), item.name(),
                    item.price(), item.quantity() - 1,
                    item.pictureURL()));
        }
    }

    private boolean sameRestaurantCheck(Item item){
        return item.restaurantId().equals(restaurantId);
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public BasketId getId() {
        return id;
    }

    public Map<UUID, Item> getItems() {
        return items;
    }

    public UUID getCustomerSessionId() {
        return customerSessionId;
    }

    @Override
    public String toString() {
        String itemsString = items.values().stream()
                .map(Item::toString)
                .collect(Collectors.joining(",\n    "));

        return String.format(
                "Basket{id=%s, restaurantId=%s, items=[\n    %s\n]}",
                id.id(), restaurantId, itemsString.isEmpty() ? "no items" : itemsString
        );
    }
}
