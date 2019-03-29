package domain;

import java.util.ArrayList;
import java.util.List;

import static utils.DateTimeUtils.createDateTime;

public class Movies {
    private static List<Movie> movies = new ArrayList<>();

    static {
        Movie movie1 = new Movie(1, "명량", 5);
        movie1.addPlayingTime(new PlayingTime(createDateTime("2019-03-29 08:00"), createDateTime("2019-03-29 09:50")));
        movie1.addPlayingTime(new PlayingTime(createDateTime("2019-03-29 10:30"), createDateTime("2019-03-29 12:20")));
        movie1.addPlayingTime(new PlayingTime(createDateTime("2019-03-29 13:00"), createDateTime("2019-03-29 14:50")));
        movie1.addPlayingTime(new PlayingTime(createDateTime("2019-03-29 15:30"), createDateTime("2019-03-29 17:20")));
        movies.add(movie1);

        Movie movie2 = new Movie(2, "극한직업", 3);
        movie2.addPlayingTime(new PlayingTime(createDateTime("2019-03-29 08:00"), createDateTime("2019-03-29 09:50")));
        movie2.addPlayingTime(new PlayingTime(createDateTime("2019-03-29 10:30"), createDateTime("2019-03-29 12:20")));
        movie2.addPlayingTime(new PlayingTime(createDateTime("2019-03-29 13:00"), createDateTime("2019-03-29 14:50")));
        movie2.addPlayingTime(new PlayingTime(createDateTime("2019-03-29 15:30"), createDateTime("2019-03-29 17:20")));
        movies.add(movie2);
    }

    public static List<Movie> getMovies() {
        return movies;
    }
}
