import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class MovieApplication {
    private static ReserveStatusList reserveStatusList = new ReserveStatusList();
    private static List<Movie> movies = MovieRepository.getMovies();
    private static TotalPrice totalPrice;
    private static Point point;
    private static PurchaseMean purchaseMean;

    public static void main(String[] args) {
        OutputView.printMovies(movies);

        selectMovie();
        while (InputView.inputDoneOrNot() != 1) {
            selectMovie();
        }

        OutputView.printResult(reserveStatusList);
        point = getPoint();
        totalPrice = new TotalPrice(calculateTotalPriceWithoutDiscount(reserveStatusList), point);

        // TODO 결제 수단 추가
        //setPurchaseMean();
        setTotalPrice();
        OutputView.printTotalPrice(totalPrice);
    }

    private static PurchaseMean setPurchaseMean() {
        int purchaseMean = InputView.getPurchaseMean();

        try {
            return new PurchaseMean(purchaseMean);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return setPurchaseMean();
        }
    }

    private static void setTotalPrice() {
        totalPrice = new TotalPrice(calculateTotalPriceWithoutDiscount(reserveStatusList), point);
    }

    private static Point getPoint() {
        int inputPoint = InputView.inputPoint();

        try {
            return new Point(inputPoint);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getPoint();
        }
    }

    private static int calculateTotalPriceWithoutDiscount(ReserveStatusList reserveStatusList) {
        return reserveStatusList.getTotalPrice();
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
