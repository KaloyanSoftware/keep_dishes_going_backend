package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalTime;

@Embeddable
public class DayScheduleEmbeddable {
    @Column(name = "start_time", nullable = false)
    private LocalTime start;

    @Column(name = "end_time", nullable = false)
    private LocalTime end;

    protected DayScheduleEmbeddable() {}

    public DayScheduleEmbeddable(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalTime getStart() { return start; }
    public LocalTime getEnd() { return end; }
}
