package domain;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private static final char NEW_LINE = '\n';

    private final int id;
    private final String name;
    private List<PlaySchedule> playSchedules = new ArrayList<>();

    public Movie(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addPlaySchedule(PlaySchedule playSchedule) {
        playSchedules.add(playSchedule);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (PlaySchedule playSchedule : playSchedules) {
            sb.append(playSchedule);
        }
        return id + " - " + name + ", " + NEW_LINE
                + sb.toString();
    }
}
