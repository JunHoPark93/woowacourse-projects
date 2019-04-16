package utils;

import domain.Movie;

import java.util.List;

public class MovieUtil {
    public static boolean checkValidMovie(int movieId, List<Movie> movies) {
        return movies.stream().map(Movie::getId).anyMatch(i -> i == movieId);
    }
}
