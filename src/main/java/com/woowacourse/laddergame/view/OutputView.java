package com.woowacourse.laddergame.view;

import com.woowacourse.laddergame.domain.vo.LadderResultDto;
import com.woowacourse.laddergame.domain.vo.ResultNameDto;

public class OutputView {
    public static void printLadderStatus(LadderResultDto ladderResultDto) {
        System.out.println(ladderResultDto.getMadeLadderVO().toString());
    }

    public static void printLadderGameResult(String targetName, LadderResultDto ladderResultDto, ResultNameDto resultNameDto) {
        System.out.println("실행 결과");
        try {
            System.out.println(ladderResultDto.getResult(targetName));
        } catch (IllegalArgumentException e) {
            System.out.println("유효하지 않은 이름입니다");
            printLadderGameResult(targetName, ladderResultDto, InputView.inputResultName(resultNameDto));
        }
    }
}
