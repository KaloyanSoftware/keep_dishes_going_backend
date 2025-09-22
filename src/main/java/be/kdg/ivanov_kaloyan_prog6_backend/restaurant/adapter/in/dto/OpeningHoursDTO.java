package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DayOfWeek;
import java.util.Map;

public record OpeningHoursDTO(Map<DayOfWeek, DayScheduleDTO> openingHours) {
}
