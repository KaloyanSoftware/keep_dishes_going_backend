package be.kdg.ivanov_kaloyan_prog6_backend.common.events;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record OrderPlacedEvent (UUID id, LocalDateTime evetPit, EventCatalog eventCatalog,
                                UUID orderId, UUID restaurantId, BigDecimal total, Map<String, Integer> orderLines,
                                String street, String number, String postalCode, String city) implements DomainEvent {

    public OrderPlacedEvent(UUID orderId, UUID restaurantId, BigDecimal total, Map<String, Integer> orderLines, String number,
                            String street, String postalCode, String city) {
        this(UUID.randomUUID(), LocalDateTime.now(), EventCatalog.ORDER_PLACED, orderId ,
                restaurantId, total, orderLines, street, number, postalCode, city);
    }

    @Override
    public LocalDateTime eventPit() {
        return evetPit;
    }
}
