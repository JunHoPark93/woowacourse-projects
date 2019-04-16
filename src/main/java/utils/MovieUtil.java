package utils;

import domain.Movie;

import java.util.List;

public class MovieUtil {
    public static boolean checkValidMovie(int movieId, List<Movie> movies) {
        return movies.stream().map(Movie::getId).anyMatch(i -> i == movieId);
    }

    public static boolean checkValidMovieSchedule(int schedule, Movie movie) {
        return movie.checkValidSchedule(schedule);
    }

    public static Movie getMovie(int movieId, List<Movie> movies) {
        // TODO depth 줄이기
        for (Movie m : movies) {
            if (m.getId() == movieId) {
                return m;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 입력입니다");
    }
}
