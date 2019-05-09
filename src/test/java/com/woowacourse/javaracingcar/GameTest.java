package com.woowacourse.javaracingcar;

import com.woowacourse.javaracingcar.domain.Car;
import com.woowacourse.javaracingcar.dto.CarDto;
import com.woowacourse.javaracingcar.util.interfaces.NumberGenerator;
import com.woowacourse.javaracingcar.testgenerator.TestNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {
    private static final int CUSTOM_LOOP = 3;
    private NumberGenerator numberGenerator;
    private Game game;
    private List<Car> cars;

    @BeforeEach
    void setUp() {
        cars = new ArrayList<>();
        cars.add(new Car("pobi"));
        cars.add(new Car("crong"));
        cars.add(new Car("honux"));
    }

    @Test
    public void 자동차_전진() {
        // given
        int[] expected = {1,2,5};
        numberGenerator = new TestNumberGenerator(expected);
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("pobi"));
        game = new Game(numberGenerator, cars);
        CarDto expectedWinnerCar = new CarDto("pobi", 1);

        // when
        for (int i = 0; i < expected.length; i++) {
            game.play();
        }

        // then
        assertThat(game.getWinners())
                .hasSize(1)
                .contains(expectedWinnerCar);
    }

    @Test
    public void 정상_게임_종료_우승자_1명반환() {
        // given
        int[] expected = {1,2,3,1,2,4,4,5,6};
        numberGenerator = new TestNumberGenerator(expected);
        CarDto expectedWinnerCar = new CarDto("honux", 2);
        game = new Game(numberGenerator, cars);

        // when
        for (int i = 0; i < CUSTOM_LOOP; i++) {
            game.play();
        }

        // then
        assertThat(game.getWinners())
                .hasSize(1)
                .contains(expectedWinnerCar);
    }

    @Test
    public void 정상_게임_종료_공동_우승자반환() {
        // given
        int[] expected ={4,1,4,5,2,5,6,4,6};
        numberGenerator = new TestNumberGenerator(expected);
        List<CarDto> expectedWinnerList = new ArrayList<>();
        expectedWinnerList.add(new CarDto("pobi", 3));
        expectedWinnerList.add(new CarDto("honux", 3));
        game = new Game(numberGenerator, cars);

        // when
        for (int i = 0; i < CUSTOM_LOOP; i++) {
            game.play();
        }
        List<CarDto> actualWinnerList = game.getWinners();

        // then
        assertThat(actualWinnerList)
                .hasSize(2)
                .isEqualTo(expectedWinnerList);
    }

    @Test
    void 정상_게임_종료_아무도_우승을_하지_못한경우() {
        //given
        int[] expected = {1,1,1,1,1,1,1,1,1};
        numberGenerator = new TestNumberGenerator(expected);
        List<CarDto> expectedWinnerList = new ArrayList<>(); // 비어 있는 리스트 반환

        game = new Game(numberGenerator, cars);

        // when
        for (int i = 0; i < CUSTOM_LOOP; i++) {
            game.play();
        }
        List<CarDto> actualWinnerList = game.getWinners();

        // then
        assertThat(actualWinnerList)
                .hasSize(0)
                .isEqualTo(expectedWinnerList);
    }
}