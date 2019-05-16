package com.woowacourse.laddergame.view;

import com.woowacourse.laddergame.domain.vo.LadderGameResultVO;
import com.woowacourse.laddergame.domain.vo.LadderStatusVO;

public class OutputView {
    public static void printLadderStatus(LadderStatusVO ladderStatusVO) {
        System.out.println(ladderStatusVO.getPlayerNames());
        System.out.println(ladderStatusVO.getLadderShape());
    }

    // TODO
    public static void printLadderGameResult(LadderGameResultVO ladderGameResultVO) {

    }
}
