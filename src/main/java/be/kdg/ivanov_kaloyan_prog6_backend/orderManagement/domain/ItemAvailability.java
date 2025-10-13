package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import java.util.UUID;

public class ItemAvailability {
    private UUID restaurantId;

    private UUID dishId;

    private boolean published;

    private boolean outOfStock;
}
