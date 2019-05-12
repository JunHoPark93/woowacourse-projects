package com.woowacourse.javaracingcar;

import com.woowacourse.javaracingcar.domain.Car;
import com.woowacourse.javaracingcar.domain.Cars;
import com.woowacourse.javaracingcar.testgenerator.TestNumberGenerator;
import com.woowacourse.javaracingcar.util.interfaces.NumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {
    private static final int CUSTOM_LOOP = 3;
    private NumberGenerator numberGenerator;
    private Game game;
    private Cars cars;
    private List<String> names;

    @BeforeEach
    void setUp() {
        names = Arrays.asList("pobi", "crong", "honux");
        cars = new Cars(names);
    }

    @Test
    public void 자동차_전진() {
        // given
        int[] expected = {1, 2, 5};
        numberGenerator = new TestNumberGenerator(expected);
        Cars cars = new Cars(Arrays.asList("pobi"));
        game = new Game(numberGenerator, cars, 3);
        Car movedPobi = new Car("pobi", 1);

        // when
        for (int i = 0; i < expected.length; i++) {
            game.play();
        }

        // then
        assertThat(game.getGameResult().getWinnerCars())
                .hasSize(1)
                .containsExactly(movedPobi);
    }

    @Test
    public void 정상_게임_종료_우승자_1명반환() {
        // given
        int[] expected = {1, 2, 3, 1, 2, 4, 4, 5, 6};
        numberGenerator = new TestNumberGenerator(expected);
        game = new Game(numberGenerator, cars, 3);
        Car winnerHonux = new Car("honux", 2);

        // when
        for (int i = 0; i < CUSTOM_LOOP; i++) {
            game.play();
        }

        // then
        assertThat(game.getGameResult().getWinnerCars())
                .hasSize(1)
                .containsExactly(winnerHonux);
    }

    @Test
    public void 정상_게임_종료_공동_우승자반환() {
        // given
        int[] expected = {4, 1, 4, 5, 2, 5, 6, 4, 6};
        numberGenerator = new TestNumberGenerator(expected);
        game = new Game(numberGenerator, cars, 3);
        Car winnerPobi = new Car("pobi", 3);
        Car winnerHonux = new Car("honux", 3);

        // when
        for (int i = 0; i < CUSTOM_LOOP; i++) {
            game.play();
        }

        // then
        assertThat(game.getGameResult().getWinnerCars())
                .hasSize(2)
                .containsExactly(winnerPobi, winnerHonux);
    }

    @Test
    void 정상_게임_종료_아무도_우승을_하지_못한경우() {
        //given
        int[] expected = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        numberGenerator = new TestNumberGenerator(expected);
        game = new Game(numberGenerator, cars, 3);

        // when
        for (int i = 0; i < CUSTOM_LOOP; i++) {
            game.play();
        }

        // then
        assertThat(game.getGameResult().getWinnerCars())
                .hasSize(0)
                .isEmpty();
    }
}