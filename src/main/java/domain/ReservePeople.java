package domain;

public class ReservePeople {
    private int people;

    public ReservePeople(int people) {
        if (people < 0) {
            throw new IllegalArgumentException("예약은 최소 1명입니다.");
        }
        this.people = people;
    }

    public int getPeople() {
        return people;
    }

    @Override
    public String toString() {
        return "예약 인원:" + people + "명";
    }
}
