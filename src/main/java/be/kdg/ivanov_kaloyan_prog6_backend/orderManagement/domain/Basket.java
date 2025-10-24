package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.BasketLineNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.InvalidBasketLineException;

import java.math.BigDecimal;
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

    public void addLine(UUID restaurantId, UUID dishId, String name, BigDecimal price, String picturerURL) {
        if(!sameRestaurantCheck(restaurantId)){
            throw new InvalidBasketLineException("You're not allowed to add dishes from different restaurants to your basket!");
        }

        BasketLine basketLine = basketLines.get(dishId);

        if(basketLine == null){
            basketLines.put(dishId, new BasketLine(restaurantId, dishId, name, price, picturerURL));
        }else{
            basketLine.increaseQuantity();
            basketLines.put(dishId, basketLine);
        }
    }

    public void decreaseLineQuantity(UUID dishId) {

        BasketLine basketLine = basketLines.get(dishId);

        if(basketLine == null){
            throw new BasketLineNotFoundException("Basket line with id: " + dishId + " not found!");
        }

        if(basketLine.decreaseQuantity() == 0 ){
            basketLines.remove(dishId);
        }else{
            basketLines.put(basketLine.getDishId(), basketLine);
        }
    }

    private boolean sameRestaurantCheck(UUID restaurantId){
        return this.restaurantId.equals(restaurantId);
    }

    public boolean empty(){
        return basketLines.isEmpty();
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
                "Basket{id=%s, restaurantId=%s, basketLines=[\n    %s\n]}",
                id.id(), restaurantId, itemsString.isEmpty() ? "no basketLines" : itemsString
        );
    }
}
