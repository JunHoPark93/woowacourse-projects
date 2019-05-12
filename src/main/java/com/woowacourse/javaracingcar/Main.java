package com.woowacourse.javaracingcar;

import com.woowacourse.javaracingcar.domain.Cars;
import com.woowacourse.javaracingcar.util.CarFactory;
import com.woowacourse.javaracingcar.util.RacingCarUtil;
import com.woowacourse.javaracingcar.util.RandomNumberGenerator;
import com.woowacourse.javaracingcar.util.interfaces.NumberGenerator;
import com.woowacourse.javaracingcar.view.ConsoleUtilInterface;
import com.woowacourse.javaracingcar.view.interfaces.UserInterface;

import java.util.List;

public class Main {
    private static UserInterface userInterface = new ConsoleUtilInterface();
    private static NumberGenerator numberGenerator = new RandomNumberGenerator();
    public static void main(String[] args) {
        Cars cars = takeCarNamesFromUserInput();
        int tries = takeTriesFromUserInput();

        // 게임 진행
        Game game = new Game(numberGenerator, cars, tries);
        while (!game.isEnd()) {
            userInterface.printResult(game.play());
        }

        // 결과 출력
        userInterface.printWinners(game.getGameResult());
    }

    private static Cars takeCarNamesFromUserInput() {
        try {
            List<String> names = userInterface.promptUserNames();
            return CarFactory.getCarsWithNames(names);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return takeCarNamesFromUserInput();
        }
    }

    private static int takeTriesFromUserInput() {
        try {
            String input = userInterface.promptTries();
            int tries = RacingCarUtil.convertTriesStringToInteger(input);
            RacingCarUtil.checkValidTriesInput(tries);
            return tries;
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다");
            return takeTriesFromUserInput();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return takeTriesFromUserInput();
        }
    }
}
