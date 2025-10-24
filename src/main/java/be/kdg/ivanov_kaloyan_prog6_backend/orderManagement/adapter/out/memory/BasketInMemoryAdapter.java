package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.memory;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Basket;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.DeleteBasketPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.LoadBasketPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.UpdateBasketPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BasketInMemoryAdapter implements LoadBasketPort, UpdateBasketPort, DeleteBasketPort {
    private final Map<UUID, Basket> baskets = new HashMap<>();

    @Override
    public Optional<Basket> loadBy(UUID customerSessionId) {
        return baskets.values().stream()
                .filter(basket -> basket.getCustomerSessionId().equals(customerSessionId))
                .findFirst();
    }

    @Override
    public Optional<Basket> loadByBasketId(UUID basketId) {
        return Optional.ofNullable(baskets.get(basketId));
    }

    @Override
    public Basket save(Basket basket) {
        baskets.put(basket.getId().id(), basket);
        return basket;
    }

    @Override
    public void delete(Basket basket) {
        baskets.remove(basket.getId().id());
    }
}
