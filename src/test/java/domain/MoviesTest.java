package domain;

import org.junit.Test;

import java.util.List;

public class MoviesTest {
    @Test
    public void 영화_출력() {
        List<Movie> movies = Movies.getMovies();
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
}