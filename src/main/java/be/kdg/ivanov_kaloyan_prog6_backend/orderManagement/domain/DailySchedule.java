package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import java.time.LocalTime;

public record DailySchedule(LocalTime start, LocalTime end) {
}
