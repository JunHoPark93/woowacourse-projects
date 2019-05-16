package com.woowacourse.laddergame.view;

import com.woowacourse.laddergame.domain.vo.LadderVO;

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

    // TODO
    public static String inputGameResult() {
        System.out.println("실행 결과를 입력하세요. (결과는 쉼표(,)로 구분하세요)");
        return scanner.nextLine();
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
}
