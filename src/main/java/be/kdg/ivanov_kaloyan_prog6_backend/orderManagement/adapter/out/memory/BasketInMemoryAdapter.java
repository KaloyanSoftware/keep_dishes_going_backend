package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.memory;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Basket;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.LoadBasketPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.SaveBasketPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BasketInMemoryAdapter implements LoadBasketPort, SaveBasketPort {
    private static final Logger log = LoggerFactory.getLogger(BasketInMemoryAdapter.class);
    private final Map<UUID, Basket> baskets = new HashMap<>();

    @Override
    public Optional<Basket> loadByCustomer(UUID ownerId) {
        return baskets.values().stream()
                .filter(basket -> basket.getCustomerId().equals(ownerId))
                .findFirst();
    }

    @Override
    public Optional<Basket> loadByBasketId(UUID basketId) {
        return Optional.ofNullable(baskets.get(basketId));
    }

    @Override
    public Basket save(Basket basket) {
        baskets.put(basket.getId().id(), basket);

        // Log all baskets with their items
        baskets.values().forEach(b -> log.info("{}", b));

        return basket;
    }
}
