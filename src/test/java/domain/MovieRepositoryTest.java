package domain;

import org.junit.Test;

import java.util.List;

public class MovieRepositoryTest {
    @Test
    public void 영화_출력() {
        List<Movie> movies = MovieRepository.getMovies();
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
}