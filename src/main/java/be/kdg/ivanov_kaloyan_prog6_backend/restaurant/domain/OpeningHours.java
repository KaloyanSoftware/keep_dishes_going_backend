package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import java.util.Map;

public record OpeningHours(Map<DayOfWeek, TimeRange> openingHours) {
}
