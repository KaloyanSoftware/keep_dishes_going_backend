package be.kdg.ivanov_kaloyan_prog6_backend.common.events;

import java.time.LocalDateTime;

public interface DomainEvent {
    LocalDateTime eventPit();
}
