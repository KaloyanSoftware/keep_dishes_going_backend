package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.DishProjection;

public interface UpdateDishProjectionPort {
    void update(DishProjection projection);
}
