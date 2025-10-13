package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.InvalidBasketItemException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class Basket {
    private BasketId id;

    private UUID restaurantId;

    private UUID customerId;

    private Map<UUID, Item> items = new HashMap<>();

    public Basket(UUID restaurantId, UUID customerId) {
        this.id = BasketId.create();
        this.restaurantId = restaurantId;
        this.customerId = customerId;
    }

    public void addItem(Item newItem) {
        if(!sameRestaurantCheck(newItem)){
            throw new InvalidBasketItemException("You're not allowed to add dishes from different restaurants to your basket!");
        }

        items.merge(newItem.dishId(), newItem, (existing, incoming) ->
                new Item(existing.restaurantId(),existing.dishId(), existing.name(),
                        existing.price(), existing.quantity() + incoming.quantity(),
                        existing.pictureURL(),existing.outOfStock()));

    }

    public void removeItem(UUID dishId) {
        items.remove(dishId);
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

    public UUID getCustomerId() {
        return customerId;
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
