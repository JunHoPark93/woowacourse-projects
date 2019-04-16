package domain;

import java.time.LocalDateTime;

import static utils.DateTimeUtils.format;

public class PlaySchedule {
    private final LocalDateTime startDateTime;
    private int capacity;

    public PlaySchedule(LocalDateTime startDateTime, int capacity) {
        this.startDateTime = startDateTime;
        this.capacity = capacity;
    }

    public boolean isValidCapacity(int capacity) {
        if (this.capacity < capacity) {
            throw new IllegalArgumentException("예약 인원 초과입니다.");
        }
        return true;
    }

    @Override
    public String toString() {
        return "시작시간: " + format(startDateTime) + " 예약가능인원: " + capacity + "\n";
    }

    public String printSchedule() {
        return "시작시간: " + format(startDateTime);
    }
}
