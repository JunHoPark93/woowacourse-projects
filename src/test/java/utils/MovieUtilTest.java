package utils;

import domain.Movie;
import domain.MovieRepository;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieUtilTest {
    private static List<Movie> movies = MovieRepository.getMovies();

    @Test
    public void 유효한_영화_아이디_반환() {
        // given
        int movieId = 1; // mock 데이터 중 유효한 않은 아이디

        // when
        boolean isValid = MovieUtil.checkValidMovie(movieId, movies);

        // then
        assertThat(isValid).isTrue();

    }

    @Test
    public void 유효하지_않은_영화_아이디_반환() {
        // given
        int movieId = 2; // mock 데이터 중 유효하지 않은 아이디

        // when
        boolean isValid = MovieUtil.checkValidMovie(movieId, movies);

        // then
        assertThat(isValid).isFalse();
    }

}