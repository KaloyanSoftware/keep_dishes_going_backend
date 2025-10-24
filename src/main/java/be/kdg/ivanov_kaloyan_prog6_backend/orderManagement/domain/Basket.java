package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.BasketLineNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.InvalidBasketLineException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class Basket {

    private BasketId id;

    private UUID restaurantId;

    private UUID customerSessionId;

    private Map<UUID, BasketLine> basketLines = new HashMap<>();

    public Basket(UUID restaurantId, UUID customerSessionId) {
        this.id = BasketId.create();
        this.restaurantId = restaurantId;
        this.customerSessionId = customerSessionId;
    }

    public void addLine(BasketLine newBasketLine) {
        if(!sameRestaurantCheck(newBasketLine)){
            throw new InvalidBasketLineException("You're not allowed to add dishes from different restaurants to your basket!");
        }

        if(basketLines.containsKey(newBasketLine.getId().id())){
            newBasketLine.increaseQuantity();
        }

        basketLines.put(newBasketLine.getId().id(), newBasketLine);
    }

    public void removeLine(UUID basketLineId) {

        BasketLine basketLine = basketLines.get(basketLineId);

        if(basketLine == null){
            throw new BasketLineNotFoundException("Basket line with id: " + basketLineId + " not found!");
        }

        if(basketLine.decreaseQuantity() == 0 ){
            basketLines.remove(basketLineId);
        }else{
            basketLines.put(basketLine.getId().id(), basketLine);
        }
    }

    private boolean sameRestaurantCheck(BasketLine basketLine){
        return basketLine.getRestaurantId().equals(restaurantId);
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public BasketId getId() {
        return id;
    }

    public Map<UUID, BasketLine> getBasketLines() {
        return basketLines;
    }

    public UUID getCustomerSessionId() {
        return customerSessionId;
    }

    @Override
    public String toString() {
        String itemsString = basketLines.values().stream()
                .map(BasketLine::toString)
                .collect(Collectors.joining(",\n    "));

        return String.format(
                "Basket{id=%s, restaurantId=%s, items=[\n    %s\n]}",
                id.id(), restaurantId, itemsString.isEmpty() ? "no items" : itemsString
        );
    }
}
