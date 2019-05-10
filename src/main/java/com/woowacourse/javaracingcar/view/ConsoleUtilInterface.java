package com.woowacourse.javaracingcar.view;

import com.woowacourse.javaracingcar.dto.CarDto;
import com.woowacourse.javaracingcar.view.interfaces.UserInterface;
import com.woowacourse.javaracingcar.util.RacingCarUtil;

import java.util.*;

public class ConsoleUtilInterface implements UserInterface {
    private final Scanner scanner;
    private boolean isResultTitlePrinted;

    public ConsoleUtilInterface() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public List<String> promptUserNames() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        String input = scanner.nextLine();
        String[] splitNames = RacingCarUtil.splitIntoNames(input);

        try {
            RacingCarUtil.checkValidNameInput(splitNames);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return promptUserNames();
        }

        return parseStringArrayToList(splitNames);
    }

    private List<String> parseStringArrayToList(String[] splitNames) {
        List<String> splitNameList = new ArrayList<>();
        Collections.addAll(splitNameList, splitNames);

        return splitNameList;
    }

    @Override
    public int promptTries() {
        System.out.println("시도할 회수는 몇회인가요?");
        String input = scanner.nextLine();

        try {
            int tries = RacingCarUtil.convertTriesStringToInteger(input);
            RacingCarUtil.checkValidTriesInput(tries);
            return tries;
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다");
            return promptTries();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return promptTries();
        }
    }

    @Override
    public void printResult(List<CarDto> cars) {
        checkResultTitlePrinted();
        for (CarDto c : cars) {
            System.out.println(c);
        }
        System.out.println();
    }

    @Override
    public void printWinners(List<CarDto> winners) {
        if (winners.size() == 0) {
            System.out.println("아무도 출발하지 못하여 우승자가 존재하지 않습니다.");
            return;
        }
        String names = RacingCarUtil.joinCarNames(winners);
        System.out.println(names + "가 최종 우승했습니다.");
    }

    @Override
    public List<String> onInvalidUserNames() {
        System.out.println("잘못된 입력입니다");
        return promptUserNames();
    }

    @Override
    public int onInvalidTries() {
        System.out.println("잘못된 입력입니다");
        return promptTries();
    }

    private boolean isValidTries(int tries) {
        return tries > 0;
    }

    private void checkResultTitlePrinted() {
        if (!isResultTitlePrinted) {
            System.out.println("실행 결과");
            isResultTitlePrinted = true;
        }
    }
}
