import domain.Movie;
import domain.MovieRepository;

import java.util.List;
import java.util.Scanner;

public class MovieApplication {
    public static void main(String[] args) {
        List<Movie> movies = MovieRepository.getMovies();
        for (Movie movie : movies) {
            System.out.println(movie);
        }

        System.out.println("## 예약할 영화를 선택하세요.");
        Scanner scanner = new Scanner(System.in);
        int movieId = scanner.nextInt();

        // TODO 구현 진행
    }
}
