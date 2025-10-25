package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.mappers.OrderMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.repositories.OrderRepository;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Order;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.UpdateOrderPort;
import org.springframework.stereotype.Repository;

@Repository
public class OrderJpaAdapter implements UpdateOrderPort {
    private final OrderRepository orderRepository;

    private OrderMapper orderMapper;

    public OrderJpaAdapter(final OrderRepository orderRepository,
                           final OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public Order update(Order order) {
        return orderMapper.toDomain(orderRepository.save(orderMapper.toJpa(order)));
    }
}
