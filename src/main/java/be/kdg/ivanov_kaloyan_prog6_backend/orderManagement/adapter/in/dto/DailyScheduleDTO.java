package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.dto;

import java.time.LocalTime;

public record DailyScheduleDTO(LocalTime start, LocalTime end){
    public static DailyScheduleDTO create(LocalTime start, LocalTime end) {
        return new DailyScheduleDTO(start, end);
    }
}
