package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.DishProjection;
import java.util.List;
import java.util.UUID;

public interface LoadDishProjectionPort {
    List<DishProjection> loadAllBy(UUID restaurantId);
}
