package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record DishSavedProjectionCommand (UUID dishId, UUID restaurantId,
                                          String stockStatus, String name, String type,
                                          List<String> tags, String description,
                                          BigDecimal price, String pictureURL){
}
