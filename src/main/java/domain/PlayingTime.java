package domain;

import java.time.LocalDateTime;

import static utils.DateTimeUtils.format;

public class PlayingTime {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    public PlayingTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "상영시간 : " +
                format(startDateTime) + " ~ " + format(endDateTime) + "\n";
    }
}
