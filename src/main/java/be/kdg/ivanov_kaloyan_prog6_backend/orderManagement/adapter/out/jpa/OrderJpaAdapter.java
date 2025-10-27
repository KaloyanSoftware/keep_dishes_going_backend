package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.mappers.OrderMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.repositories.OrderRepository;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Order;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.LoadOrderPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.UpdateOrderPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Qualifier("jpa")
public class OrderJpaAdapter implements UpdateOrderPort, LoadOrderPort {
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

    @Override
    public Optional<Order> loadBy(UUID id) {
        return orderRepository.findById(id).map(orderMapper::toDomain);
    }
}
