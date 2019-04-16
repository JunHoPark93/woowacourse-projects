package view;

import domain.Movie;
import domain.ReserveStatusList;
import domain.TotalPrice;

import java.util.List;

public class OutputView {
    public static void printMovies(List<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    public static void printResult(ReserveStatusList reserveStatusList) {
        reserveStatusList.printResult();
    }

    public static void printTotalPrice(TotalPrice totalPrice) {
        System.out.println(totalPrice.toString());
    }
}
