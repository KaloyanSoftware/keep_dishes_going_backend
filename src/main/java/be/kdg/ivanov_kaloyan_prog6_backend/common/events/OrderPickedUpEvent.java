package be.kdg.ivanov_kaloyan_prog6_backend.common.events;

import org.jmolecules.event.annotation.Externalized;

import java.time.LocalDateTime;
import java.util.UUID;

@Externalized("kdg.events::#{'delivery.' + #this.restaurantId() + '.order.pickedup.v1'}")
public record OrderPickedUpEvent(UUID eventId, LocalDateTime occurredAt, UUID restaurantId, UUID orderId){}
