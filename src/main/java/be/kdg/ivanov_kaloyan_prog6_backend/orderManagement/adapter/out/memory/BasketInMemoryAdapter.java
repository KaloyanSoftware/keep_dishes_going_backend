package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.memory;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Basket;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.LoadBasketPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.SaveBasketPort;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BasketInMemoryAdapter implements LoadBasketPort, SaveBasketPort {
    private final Map<UUID, Basket> baskets = new HashMap<>();

    @Override
    public Optional<Basket> loadBy(UUID id) {
        return baskets.values().stream().
                filter(basket -> basket.getId().id() == id).findFirst();
    }

    @Override
    public Basket save(Basket basket) {
        return baskets.put(basket.getId().id(), basket);
    }
}
