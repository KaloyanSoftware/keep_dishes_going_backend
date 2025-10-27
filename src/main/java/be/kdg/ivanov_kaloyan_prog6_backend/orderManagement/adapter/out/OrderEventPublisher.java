package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Order;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.UpdateOrderPort;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisher implements UpdateOrderPort {
    private final ApplicationEventPublisher eventPublisher;

    public OrderEventPublisher(final ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Order update(Order order) {
        order.getUncommittedEvents().forEach(eventPublisher::publishEvent);
        return order;
    }
}
