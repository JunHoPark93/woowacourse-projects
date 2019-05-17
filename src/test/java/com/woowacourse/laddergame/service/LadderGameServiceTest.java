package com.woowacourse.laddergame.service;

import com.woowacourse.laddergame.domain.*;
import com.woowacourse.laddergame.domain.vo.LadderDto;
import com.woowacourse.laddergame.domain.vo.LadderResultDto;
import com.woowacourse.laddergame.domain.vo.MadeLadderVO;
import com.woowacourse.laddergame.domain.vo.WinnerVO;
import com.woowacourse.laddergame.util.NaturalNumber;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class LadderGameServiceTest {
    @Test
    void 사다리_통합테스트_커스텀() {
        LadderDto ladderDto = new LadderDto();
        ladderDto.setNames("pobi,honux,tim,crong");
        ladderDto.setHeight("3");
        ladderDto.setResult("꽝,꽝,꽝,300원");

        // 사용자 입력
        Players players = new Players();
        players.add(new Player("pobi"));
        players.add(new Player("honux"));
        players.add(new Player("tim"));
        players.add(new Player("crong"));

        Ladder ladder = new Ladder(new NaturalNumber(3), new NaturalNumber(4));
        ladder.putBridge(new NaturalNumber(1), new NaturalNumber(1));
        ladder.putBridge(new NaturalNumber(1), new NaturalNumber(3));
        ladder.putBridge(new NaturalNumber(2), new NaturalNumber(2));
        ladder.putBridge(new NaturalNumber(3), new NaturalNumber(3));

        Results results = new Results();
        results.add(new Result("꽝"));
        results.add(new Result("꽝"));
        results.add(new Result("꽝"));
        results.add(new Result("300원"));

        HashMap<String, String> winners = new HashMap<>();
        winners.put("pobi", "300원");
        winners.put("honux", "꽝");
        winners.put("tim", "꽝");
        winners.put("crong", "꽝");

        MadeLadderVO madeLadderVO = new MadeLadderVO(players, ladder, results);
        WinnerVO winnerVO = new WinnerVO(winners);
        LadderResultDto expectedLadderResultDto = new LadderResultDto();
        expectedLadderResultDto.setMadeLadderVO(madeLadderVO);
        expectedLadderResultDto.setWinnerVO(winnerVO);

        LadderGameService ladderGameService = new LadderGameService();
        LadderResultDto actualLadderResultDto = ladderGameService.play(ladderDto);

        System.out.println(actualLadderResultDto.getMadeLadderVO());
        System.out.println(actualLadderResultDto.getWinnerVO());
        System.out.println(actualLadderResultDto.getMadeLadderVO());
    }
}