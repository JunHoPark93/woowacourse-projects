package com.woowacourse.javaracingcar;

import com.woowacourse.javaracingcar.domain.Car;
import com.woowacourse.javaracingcar.util.interfaces.NumberGenerator;
import com.woowacourse.javaracingcar.view.interfaces.UserInterface;
import com.woowacourse.javaracingcar.util.RandomNumberGenerator;
import com.woowacourse.javaracingcar.view.ConsoleUtilInterface;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 사용자 입력
        UserInterface userInterface = new ConsoleUtilInterface();
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        List<String> names = userInterface.promptUserNames();
        List<Car> cars = new ArrayList<>();
        int tries = userInterface.promptTries();

        // 차량 초기화
        for (String name : names) {
            cars.add(new Car(name));
        }

        // 게임 진행
        Game game = new Game(numberGenerator, cars);
        for (int i = 0; i < tries; i++) {
            userInterface.printResult(game.play());
        }

        // 결과 출력
        userInterface.printWinners(game.getWinners());
    }
}
