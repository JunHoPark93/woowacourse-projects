package com.woowacourse.laddergame.service;

import com.woowacourse.laddergame.domain.vo.LadderStatusVO;
import com.woowacourse.laddergame.domain.vo.LadderVO;
import org.junit.jupiter.api.Test;

class LadderGameServiceTest {
    @Test
    void 사다리_통합테스트() {
        LadderVO ladderVO = new LadderVO();
        String playerNames = "pobi,crong,honux";
        String height = "4";
        ladderVO.setNames(playerNames);
        ladderVO.setHeight(height);

        LadderGameService ladderGameService = new LadderGameService();
        ladderGameService.drawLadder(ladderVO);

        LadderStatusVO statusVO = ladderGameService.getInitialLadder();

        System.out.println(statusVO.getPlayerNames());
        System.out.println(statusVO.getLadderShape());
    }
}