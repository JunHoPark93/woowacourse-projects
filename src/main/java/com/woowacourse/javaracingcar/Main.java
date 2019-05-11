package com.woowacourse.javaracingcar;

import com.woowacourse.javaracingcar.domain.Car;
import com.woowacourse.javaracingcar.util.CarFactory;
import com.woowacourse.javaracingcar.util.RandomNumberGenerator;
import com.woowacourse.javaracingcar.util.interfaces.NumberGenerator;
import com.woowacourse.javaracingcar.view.ConsoleUtilInterface;
import com.woowacourse.javaracingcar.view.interfaces.UserInterface;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 사용자 입력
        UserInterface userInterface = new ConsoleUtilInterface();
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        List<String> names = userInterface.promptUserNames();
        int tries = userInterface.promptTries();

        // 차량 초기화
        List<Car> cars = CarFactory.getCarsWithNames(names);

        // 게임 진행
        Game game = new Game(numberGenerator, cars, tries);
        while (!game.isEnd()) {
            userInterface.printResult(game.play());
        }

        // 결과 출력
        userInterface.printWinners(game.getGameResult());
    }
}
