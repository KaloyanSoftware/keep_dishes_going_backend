package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.OrderProjection;

public interface UpdateOrderProjectionPort {
    OrderProjection update(OrderProjection orderProjection);
}
