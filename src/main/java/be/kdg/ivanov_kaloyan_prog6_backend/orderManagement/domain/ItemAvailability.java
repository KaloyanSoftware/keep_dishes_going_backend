package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import java.util.UUID;

public class ItemAvailability {
    private UUID restaurantId;

    private UUID dishId;

    private boolean orderable;

    public ItemAvailability(UUID restaurantId, UUID dishId, boolean orderable) {
        this.restaurantId = restaurantId;
        this.dishId = dishId;
        this.orderable = orderable;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public UUID getDishId() {
        return dishId;
    }

    @Override
    public String toString() {
        return "ItemAvailability{" +
                "restaurantId=" + restaurantId +
                ", dishId=" + dishId +
                ", orderable=" + orderable +
                '}';
    }

    public boolean isOrderable() {
        return orderable;
    }

    private void setOrderable(boolean orderable) {
        this.orderable = orderable;
    }

    public void makeOrderable(boolean orderable){
        setOrderable(orderable);
    }
}
