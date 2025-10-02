package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DayOfWeek;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.OpeningHours;

import java.util.Map;
import java.util.stream.Collectors;

public record OpeningHoursDTO(Map<DayOfWeek, DayScheduleDTO> weeklySchedule) {

    public static OpeningHoursDTO from(final OpeningHours openingHours){
        Map<DayOfWeek, DayScheduleDTO> domainMap = openingHours.openingHours().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> new DayScheduleDTO(e.getValue().start(), e.getValue().end())
                ));
        return new OpeningHoursDTO(domainMap);
    }
}
