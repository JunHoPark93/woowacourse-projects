package domain;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private final int id;
    private final String name;
    private final int currentCapacity;
    private List<PlayingTime> playingSchedule = new ArrayList<>();

    public Movie(int id, String name, int currentCapacity) {
        this.id = id;
        this.name = name;
        this.currentCapacity = currentCapacity;
    }

    public void addPlayingTime(PlayingTime playingTime) {
        playingSchedule.add(playingTime);
    }

    @Override
    public String toString() {
        return id + " - " + name + ", " +
                "예매가능인원 : " + currentCapacity + "\n"
                + playingSchedule;
    }
}
