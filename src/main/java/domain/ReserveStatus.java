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

    public PlaySchedule getPlaySchedule() {
        return playSchedule;
    }

    public boolean isOneHourOverLap(ReserveStatus reserveStatus) {
        System.out.println(reserveStatus.getPlaySchedule());
        System.out.println(this.playSchedule.getStartDateTime());
        return playSchedule.isOneHourOverLap(reserveStatus.getPlaySchedule());
    }
    @Override
    public String toString() {
        return movie.printMovie() + "\n" + playSchedule.printSchedule() + "\n" + reservePeople.toString() + "\n";
    }
}
