package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.ItemAvailability;

public interface UpdateItemAvailabilityPort {
    ItemAvailability update(ItemAvailability itemAvailability);
}
