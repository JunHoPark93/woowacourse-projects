package com.woowacourse.laddergame.view;

import com.woowacourse.laddergame.domain.vo.LadderVO;
import com.woowacourse.laddergame.domain.vo.ResultNameVO;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static LadderVO inputPlayerNames(LadderVO ladderVO) {
        try {
            System.out.println("참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)");
            ladderVO.setNames(scanner.nextLine());
            return ladderVO;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputPlayerNames(ladderVO);
        }

    }

    public static LadderVO inputGameResult(LadderVO ladderVO) {
        try {
            System.out.println("실행 결과를 입력하세요. (결과는 쉼표(,)로 구분하세요)");
            ladderVO.setResult(scanner.nextLine());
            return ladderVO;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputGameResult(ladderVO);
        }
    }

    public static LadderVO inputHeight(LadderVO ladderVO) {
        try {
            System.out.println("최대 사다리 높이는 몇 개인가요?");
            ladderVO.setHeight(scanner.nextLine());
            return ladderVO;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputHeight(ladderVO);
        }
    }

    public static ResultNameVO inputResultName(ResultNameVO resultNameVO) {
        try {
            System.out.println("결과를 보고 싶은 사람은?");
            String resultName = scanner.nextLine();
            checkExit(resultName);
            resultNameVO.setName(resultName);
            return resultNameVO;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputResultName(resultNameVO);
        }
    }

    private static void checkExit(String input) {
        if (input.equals("-1")) {
            System.exit(0);
        }
    }
}
