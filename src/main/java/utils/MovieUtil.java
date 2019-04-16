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

    public static boolean checkValidPeopleCapacity(Movie movie, int movieSchedule, int peopleCapacity) {
        if (peopleCapacity <= 0) {
            return false;
        }
        return movie.checkValidPeopleCapacity(movieSchedule, peopleCapacity);
    }
}
