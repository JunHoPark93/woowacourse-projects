package com.woowacourse.laddergame.view;

import com.woowacourse.laddergame.domain.vo.LadderResultDto;

public class OutputView {
    public static void printLadderStatus(LadderResultDto ladderResultDto) {
        System.out.println(ladderResultDto.getMadeLadderVO().toString());
    }

    public static void printLadderGameResult(String targetName, LadderResultDto ladderResultDto) {
        System.out.println("실행 결과");
        System.out.println(ladderResultDto.getResult(targetName));
    }
}
