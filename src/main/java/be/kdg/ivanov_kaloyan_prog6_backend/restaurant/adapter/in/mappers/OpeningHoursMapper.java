package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.mappers;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto.DayScheduleDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto.OpeningHoursDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DayOfWeek;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DaySchedule;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.OpeningHours;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.EnumMap;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface OpeningHoursMapper {

    // Ensure non-null map for immutable record ctor
    @Mapping(target = "openingHours",
            expression = "java(dto.weeklySchedule() == null ? java.util.Map.of() : mapSchedule(dto.weeklySchedule()))")
    OpeningHours toDomain(OpeningHoursDTO dto);

    // Helper used in the expression; MapStruct will call the abstract method below for values.
    default Map<DayOfWeek, DaySchedule> mapSchedule(Map<DayOfWeek, DayScheduleDTO> src) {
        Map<DayOfWeek, DaySchedule> out = new EnumMap<>(DayOfWeek.class);
        if (src != null) {
            for (var e : src.entrySet()) {
                out.put(e.getKey(), toDomain(e.getValue()));
            }
        }
        return java.util.Collections.unmodifiableMap(out);
    }

    DaySchedule toDomain(DayScheduleDTO dto);
}