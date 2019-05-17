package com.woowacourse.laddergame.view;

import com.woowacourse.laddergame.domain.vo.LadderDto;
import com.woowacourse.laddergame.domain.vo.ResultNameDto;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static LadderDto inputPlayerNames(LadderDto ladderDto) {
        try {
            System.out.println("참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)");
            ladderDto.setNames(scanner.nextLine());
            return ladderDto;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputPlayerNames(ladderDto);
        }

    }

    public static LadderDto inputGameResult(LadderDto ladderDto) {
        try {
            System.out.println("실행 결과를 입력하세요. (결과는 쉼표(,)로 구분하세요)");
            ladderDto.setResult(scanner.nextLine());
            return ladderDto;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputGameResult(ladderDto);
        }
    }

    public static LadderDto inputHeight(LadderDto ladderDto) {
        try {
            System.out.println("최대 사다리 높이는 몇 개인가요?");
            ladderDto.setHeight(scanner.nextLine());
            return ladderDto;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputHeight(ladderDto);
        }
    }

    public static ResultNameDto inputResultName(ResultNameDto resultNameDto) {
        try {
            System.out.println("결과를 보고 싶은 사람은?");
            String resultName = scanner.nextLine();
            checkExit(resultName);
            resultNameDto.setName(resultName);
            return resultNameDto;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputResultName(resultNameDto);
        }
    }

    private static void checkExit(String input) {
        if (input.equals("-1")) {
            System.exit(0);
        }
    }
}
