package com.woowacourse.laddergame.service;

import com.woowacourse.laddergame.domain.vo.LadderGameResultVO;
import com.woowacourse.laddergame.domain.vo.LadderStatusVO;
import com.woowacourse.laddergame.domain.vo.LadderVO;
import com.woowacourse.laddergame.domain.vo.ResultNameVO;
import org.junit.jupiter.api.Test;

class LadderGameServiceTest {
    @Test
    void 사다리_통합테스트() {
        LadderVO ladderVO = new LadderVO();
        String playerNames = "pobi,crong,honux";
        String height = "4";
        String results = "꽝,아이스크림,500원";
        ladderVO.setNames(playerNames);
        ladderVO.setHeight(height);
        ladderVO.setResult(results);

        LadderGameService ladderGameService = new LadderGameService();
        ladderGameService.drawLadder(ladderVO);

        // TODO 사람검증
        ResultNameVO resultNameVO = new ResultNameVO();
        resultNameVO.setName("crong");
        ladderGameService.playLadder(resultNameVO);

        LadderStatusVO statusVO = ladderGameService.getInitialLadder();

        System.out.println(statusVO.getPlayerNames());
        System.out.println(statusVO.getLadderShape());
        System.out.println(statusVO.getLadderResult());

        LadderGameResultVO ladderGameResultVO = ladderGameService.playLadder(resultNameVO);
        System.out.println(ladderGameResultVO.getResult());
    }
}