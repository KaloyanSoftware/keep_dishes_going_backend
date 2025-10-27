package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers.OrderProjectionMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.repositories.OrderProjectionJpaRepository;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.OrderProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadOrderProjectionPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.UpdateOrderProjectionPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
@Qualifier("jpa")
public class OrderProjectionAdapter implements UpdateOrderProjectionPort, LoadOrderProjectionPort {
    private final OrderProjectionJpaRepository orderProjectionJpaRepository;

    private final OrderProjectionMapper orderProjectionMapper;

    public OrderProjectionAdapter(final OrderProjectionJpaRepository orderProjectionJpaRepository,
                                  final OrderProjectionMapper orderProjectionMapper) {
        this.orderProjectionJpaRepository = orderProjectionJpaRepository;
        this.orderProjectionMapper = orderProjectionMapper;
    }

    @Override
    public OrderProjection update(OrderProjection orderProjection) {
        return orderProjectionMapper.toDomain(orderProjectionJpaRepository.
                save(orderProjectionMapper.toJpa(orderProjection)));
    }

    @Override
    public Optional<OrderProjection> loadBy(UUID id) {
        return this.orderProjectionJpaRepository.findById(id).map(orderProjectionMapper::toDomain);
    }
}
