package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Basket;

import java.util.Optional;
import java.util.UUID;

public interface LoadBasketPort {
    Optional<Basket> loadByOwner(UUID ownerId);

    Optional<Basket> loadByBasketId(UUID basketId);
}
