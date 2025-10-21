package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out;

import java.util.UUID;

public interface DeleteDishProjectionPort {
    void delete(UUID dishProjectionId);
}
