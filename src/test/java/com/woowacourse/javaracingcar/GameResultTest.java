package com.woowacourse.javaracingcar;

import com.woowacourse.javaracingcar.domain.Car;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameResultTest {
    @Test
    void 정상적인_우승자_자동차_반환() {
        // given
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("pobi", 3));
        cars.add(new Car("jay", 3));
        cars.add(new Car("crong", 2));

        List<Car> expectedWinnerCars = new ArrayList<>();
        expectedWinnerCars.add(new Car("pobi", 3));
        expectedWinnerCars.add(new Car("jay", 3));

        // when
        GameResult gameResult = new GameResult(cars);

        // then
        assertThat(gameResult.getWinnerCars()).isEqualTo(expectedWinnerCars);
    }

    @Test
    void 빈_자동차들이_들어오는_경우_예외발생() {
        // given
        List<Car> emptyCars = Collections.emptyList();

        // when then
        assertThrows(IllegalArgumentException.class, () -> {
            GameResult gameResult = new GameResult(emptyCars);
        });
    }
}