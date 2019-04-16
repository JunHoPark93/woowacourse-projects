import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class MovieApplication {
    private static ReserveStatusList reserveStatusList = new ReserveStatusList();
    private static List<Movie> movies = MovieRepository.getMovies();

    public static void main(String[] args) {
        OutputView.printMovies(movies);

        selectMovie();
        while (InputView.inputDoneOrNot() != 1) {
            selectMovie();
        }

        OutputView.printResult(reserveStatusList);
    }

    private static void selectMovie() {
        Movie movie = getMovie();
        System.out.println(movie.toString());
        PlaySchedule playSchedule = getSchedule(movie);
        ReservePeople reservePeople = getPeople(playSchedule);

        try {
            reserveStatusList.putResult(new ReserveStatus(movie, playSchedule, reservePeople));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Movie getMovie() {
        try {
            int id = InputView.inputMovieId();
            return MovieRepository.getMovieWithId(id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getMovie();
        }
    }

    private static PlaySchedule getSchedule(Movie movie) {
        int schedule = InputView.inputMovieSchedule();
        try {
            movie.checkValidSchedule(schedule);
            return movie.getPlaySchedule(schedule);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getSchedule(movie);
        }
    }

    private static ReservePeople getPeople(PlaySchedule playSchedule) {
        int capacity = InputView.inputPeopleCapacity();
        try {
            playSchedule.isValidCapacity(capacity);
            return new ReservePeople(capacity);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getPeople(playSchedule);
        }
    }
}
