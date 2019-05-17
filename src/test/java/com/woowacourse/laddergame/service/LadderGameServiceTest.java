package com.woowacourse.laddergame.service;

import com.woowacourse.laddergame.domain.Ladder;
import com.woowacourse.laddergame.domain.Player;
import com.woowacourse.laddergame.domain.Players;
import com.woowacourse.laddergame.domain.vo.LadderDto;
import com.woowacourse.laddergame.domain.vo.LadderResultDto;
import com.woowacourse.laddergame.util.NaturalNumber;
import org.junit.jupiter.api.Test;

class LadderGameServiceTest {
    @Test
    void 실제_사다리_모양과_결과_확인용_메서드() {
        // given
        LadderDto ladderDto = new LadderDto();
        ladderDto.setNames("pobi,honux,tim,crong");
        ladderDto.setHeight("3");
        ladderDto.setResult("꽝,꽝,꽝,300원");

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

        // when
        LadderResultDto actualLadderResultDto = LadderGameService.play(ladderDto);

        // then
        System.out.println(actualLadderResultDto.getMadeLadderVO());
        System.out.println(actualLadderResultDto.getWinnerVO().getAllResult());
    }
}