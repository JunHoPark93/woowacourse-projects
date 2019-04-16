package domain;

public class ReserveStatus {
    private Movie movie;
    private PlaySchedule playSchedule;
    private ReservePeople reservePeople;

    public ReserveStatus(Movie movie, PlaySchedule playSchedule, ReservePeople reservePeople) {
        this.movie = movie;
        this.playSchedule = playSchedule;
        this.reservePeople = reservePeople;
    }

    @Override
    public String toString() {
        return movie.printMovie() + "\n" + playSchedule.printSchedule() + "\n" + reservePeople.toString() + "\n";
    }
}
