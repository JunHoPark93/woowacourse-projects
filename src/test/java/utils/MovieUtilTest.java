package utils;

import domain.Movie;
import domain.MovieRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieUtilTest {
    private static List<Movie> movies;

    @Before
    public void setUp() {
        movies = MovieRepository.getMovies();
    }

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

    @Test
    public void 유효한_시간표_입력() {
        // given
        int schedule = 1;
        Movie movie = movies.get(0); // 첫 번째 영화

        // when
        boolean isValid = MovieUtil.checkValidMovieSchedule(schedule, movie);

        // then
        assertThat(isValid).isTrue();
    }

    @Test
    public void 유효하지_않은_시간표_입력() {
        // given
        int schedule = 7;
        Movie movie = movies.get(0); // 첫 번째 영화

        // when
        boolean isValid = MovieUtil.checkValidMovieSchedule(schedule, movie);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    public void 유효한_인원_입력() {
        // given
        int capacity = 3;
        int schedule = 1;
        Movie movie = movies.get(0);

        // when
        boolean isValid = MovieUtil.checkValidPeopleCapacity(movie, schedule, capacity);

        // then
        assertThat(isValid).isTrue();
    }

    @Test
    public void 유효하지_않은_인원_입력() {
        // given
        int capacity = 10;
        int schedule = 1;
        Movie movie = movies.get(0);

        // when
        boolean isValid = MovieUtil.checkValidPeopleCapacity(movie, schedule, capacity);

        // then
        assertThat(isValid).isFalse();
    }

}