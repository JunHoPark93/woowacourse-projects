package view;

import domain.Movie;
import domain.ReserveStatus;
import domain.ReserveStatusList;

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
}
