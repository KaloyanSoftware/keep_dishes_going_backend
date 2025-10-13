package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import java.util.UUID;

public class ItemAvailability {
    private UUID restaurantId;

    private UUID dishId;

    private boolean published;

    private boolean inStock;

    public ItemAvailability(UUID restaurantId, UUID dishId, boolean published, boolean inStock) {
        this.restaurantId = restaurantId;
        this.dishId = dishId;
        this.published = published;
        this.inStock = inStock;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public UUID getDishId() {
        return dishId;
    }

    public boolean isPublished() {
        return published;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    @Override
    public String toString() {
        return "ItemAvailability{" +
                "restaurantId=" + restaurantId +
                ", dishId=" + dishId +
                ", published=" + published +
                ", inStock=" + inStock +
                '}';
    }
}
