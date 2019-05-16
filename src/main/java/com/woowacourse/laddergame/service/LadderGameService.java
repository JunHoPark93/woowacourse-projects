package com.woowacourse.laddergame.service;

import com.woowacourse.laddergame.domain.*;
import com.woowacourse.laddergame.domain.vo.LadderStatusVO;
import com.woowacourse.laddergame.domain.vo.LadderVO;
import com.woowacourse.laddergame.util.NaturalNumber;

public class LadderGameService {
    private MadeLadder madeLadder;

    public void drawLadder(LadderVO ladderVO) {
        Players players = new Players();
        String[] playerNames = ladderVO.getNames().split(",");

        for (String playerName: playerNames) {
            players.add(new Player(playerName));
        }

        int height = ladderVO.getHeight();
        int countOfPerson = playerNames.length;

        BooleanGenerator booleanGenerator = new RandomBooleanGenerator();
        Ladder ladder = LadderGenerator.generateLadder(new NaturalNumber(height), new NaturalNumber(countOfPerson), booleanGenerator);

        madeLadder = new MadeLadder(players, ladder);
    }

    public LadderStatusVO getInitialLadder() {
        LadderStatusVO ladderStatusVO = new LadderStatusVO();
        ladderStatusVO.setLadderShape(madeLadder.getLadderShape());
        ladderStatusVO.setPlayerNames(madeLadder.getPlayerNames());

        // TODO result 추가
        return ladderStatusVO;
    }
}

