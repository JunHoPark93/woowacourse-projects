package com.woowacourse.laddergame.service;

import com.woowacourse.laddergame.domain.*;
import com.woowacourse.laddergame.domain.vo.LadderStatusVO;
import com.woowacourse.laddergame.domain.vo.LadderVO;
import com.woowacourse.laddergame.util.NaturalNumber;

public class LadderGameService {
    private MadeLadder madeLadder;

    // TODO 분리
    public void drawLadder(LadderVO ladderVO) {
        Players players = new Players();
        Results results = new Results();
        String[] playerNames = ladderVO.getNames().split(",");
        String[] resultList = ladderVO.getResult().split(",");

        for (String playerName: playerNames) {
            players.add(new Player(playerName));
        }

        for (String result : resultList) {
            results.add(new Result(result));
        }

        int height = ladderVO.getHeight();
        int countOfPerson = playerNames.length;

        BooleanGenerator booleanGenerator = new RandomBooleanGenerator();
        Ladder ladder = LadderGenerator.generateLadder(new NaturalNumber(height), new NaturalNumber(countOfPerson), booleanGenerator);

        madeLadder = new MadeLadder(players, ladder, results);
    }

    public LadderStatusVO getInitialLadder() {
        LadderStatusVO ladderStatusVO = new LadderStatusVO();
        ladderStatusVO.setLadderShape(madeLadder.getLadderShape());
        ladderStatusVO.setPlayerNames(madeLadder.getPlayerNames());
        ladderStatusVO.setLadderResult(madeLadder.getLadderResult());

        return ladderStatusVO;
    }

    // TODO
    public void playLadder(String name) {

    }
}

