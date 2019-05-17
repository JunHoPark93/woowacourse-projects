package com.woowacourse.laddergame.service;

import com.woowacourse.laddergame.domain.*;
import com.woowacourse.laddergame.domain.vo.LadderDto;
import com.woowacourse.laddergame.domain.vo.LadderResultDto;
import com.woowacourse.laddergame.domain.vo.MadeLadderVO;
import com.woowacourse.laddergame.domain.vo.WinnerVO;
import com.woowacourse.laddergame.util.NaturalNumber;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class LadderGameService {
    // TODO 분리
    public LadderResultDto play(LadderDto ladderDto) {
        // 사다리 초기화
        Players players = new Players();
        Results results = new Results();
        String[] playerNames = ladderDto.getNames().split(",");
        String[] resultList = ladderDto.getResult().split(",");

        for (String playerName : playerNames) {
            players.add(new Player(playerName));
        }

        for (String result : resultList) {
            results.add(new Result(result));
        }

        int height = ladderDto.getHeight();
        int countOfPerson = playerNames.length;

        BooleanGenerator booleanGenerator = new RandomBooleanGenerator();
        Ladder ladder = LadderGenerator.generateLadder(new NaturalNumber(height), new NaturalNumber(countOfPerson), booleanGenerator);

        // play
        HashMap<String, String> winners = new LinkedHashMap<>();

        for (String playerName : playerNames) {
            int resultNo = ladder.takeLadder(new NaturalNumber(players.getPlayerNo(playerName)));
            Result result = results.get(new NaturalNumber(resultNo));
            winners.put(playerName, result.getResult());
        }

        LadderResultDto ladderResultDto = new LadderResultDto();
        ladderResultDto.setWinnerVO(new WinnerVO(winners));
        ladderResultDto.setMadeLadderVO(new MadeLadderVO(players, ladder, results));

        return ladderResultDto;
    }
}

