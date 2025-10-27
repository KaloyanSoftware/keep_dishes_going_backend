package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Order;

import java.util.Optional;
import java.util.UUID;

public interface LoadOrderPort {
    Optional<Order> loadBy(UUID id);
}
