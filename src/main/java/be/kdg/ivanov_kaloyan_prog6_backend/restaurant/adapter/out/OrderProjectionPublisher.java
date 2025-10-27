package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.OrderProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.UpdateOrderProjectionPort;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class OrderProjectionPublisher implements UpdateOrderProjectionPort {

    private final ApplicationEventPublisher publisher;

    public OrderProjectionPublisher(final ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public OrderProjection update(OrderProjection orderProjection) {
        orderProjection.getUncommittedEvents().forEach(publisher::publishEvent);
        return orderProjection;
    }
}
