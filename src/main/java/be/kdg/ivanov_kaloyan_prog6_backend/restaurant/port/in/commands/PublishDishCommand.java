package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands;

import java.util.UUID;

public record PublishDishCommand(UUID dishId,
                                 UUID menuId){
}
