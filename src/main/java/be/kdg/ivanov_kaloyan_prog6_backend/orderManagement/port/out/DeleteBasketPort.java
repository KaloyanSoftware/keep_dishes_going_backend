package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Basket;

public interface DeleteBasketPort {
    void delete(Basket basket);
}
