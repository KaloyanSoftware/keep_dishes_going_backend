package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DayOfWeek;
import java.util.Map;

public record WorkingHours(Map<DayOfWeek, DailySchedule> openingHours){
}
