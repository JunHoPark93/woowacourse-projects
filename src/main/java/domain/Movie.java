package domain;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private static final char NEW_LINE = '\n';

    private final int id;
    private final String name;
    private final int price;

    private List<PlaySchedule> playSchedules = new ArrayList<>();

    public Movie(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    void addPlaySchedule(PlaySchedule playSchedule) {
        playSchedules.add(playSchedule);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (PlaySchedule playSchedule : playSchedules) {
            sb.append(playSchedule);
        }
        return id + " - " + name + ", " + price + "원" + NEW_LINE
                + sb.toString();
    }

    public String printMovie() {
        return id + " - " + name + ", " + price + "원";
    }

    public int getId() {
        return id;
    }

    public boolean checkValidSchedule(int schedule) {
        int size = playSchedules.size();
        if (1 > schedule || schedule > size) {
            throw new IllegalArgumentException("해당 스케줄은 존재하지 않습니다.");
        }
        return true;
    }

    public boolean checkValidPeopleCapacity(int movieSchedule, int peopleCapacity) {
        return playSchedules.get(movieSchedule - 1).isValidCapacity(peopleCapacity);
    }

    public PlaySchedule getPlaySchedule(int schedule) {
        return playSchedules.get(schedule - 1);
    }
}
