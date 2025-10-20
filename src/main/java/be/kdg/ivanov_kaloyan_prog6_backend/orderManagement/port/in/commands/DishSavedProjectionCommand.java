package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.DishProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.FoodTagProjection;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record DishSavedProjectionCommand (UUID dishId, UUID restaurantId,
                                          DishProjection.StockStatus stockStatus, String name,  DishProjection.DishType type,
                                          List<FoodTagProjection> tags, String description,
                                          BigDecimal price, String pictureURL){
}
