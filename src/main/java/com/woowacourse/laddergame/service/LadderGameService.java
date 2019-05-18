package com.woowacourse.laddergame.service;

import com.woowacourse.laddergame.domain.*;
import com.woowacourse.laddergame.domain.vo.LadderDto;
import com.woowacourse.laddergame.domain.vo.LadderResultDto;
import com.woowacourse.laddergame.domain.vo.MadeLadderVO;
import com.woowacourse.laddergame.domain.vo.WinnerVO;
import com.woowacourse.laddergame.util.BooleanGenerator;
import com.woowacourse.laddergame.util.NaturalNumber;
import com.woowacourse.laddergame.util.RandomBooleanGenerator;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class LadderGameService {
    public static LadderResultDto play(LadderDto ladderDto) {
        Players players = getPlayers(ladderDto);
        Results results = getResults(ladderDto);
        Ladder ladder = getLadder(ladderDto, players);

        WinnerVO winnerVO = getWinners(players, ladder, results);
        MadeLadderVO madeLadderVO = new MadeLadderVO(players, ladder, results);

        return convertLadderResultDto(winnerVO, madeLadderVO);
    }

    private static Players getPlayers(LadderDto ladderDto) {
        String[] playerNames = ladderDto.getNames().split(",");
        Players players = new Players();

        for (String name : playerNames) {
            players.add(new Player(name));
        }

        return players;
    }

    private static Results getResults(LadderDto ladderDto) {
        String[] resultNames = ladderDto.getResult().split(",");
        Results results = new Results();

        for (String result : resultNames) {
            results.add(new Result(result));
        }

        return results;
    }

    private static Ladder getLadder(LadderDto ladderDto, Players players) {
        int height = ladderDto.getHeight();
        int countOfPerson = players.getPlayerCount();
        BooleanGenerator booleanGenerator = new RandomBooleanGenerator();

        Ladder ladder = new Ladder(new NaturalNumber(height), new NaturalNumber(countOfPerson));
        ladder.initLadder(booleanGenerator);

        return ladder;
    }

    private static WinnerVO getWinners(Players players, Ladder ladder, Results results) {
        Winners winners = new Winners();
        for (String playerName : players.getPlayersName()) {
            int resultNo = ladder.takeLadder(new NaturalNumber(players.getPlayerNo(playerName)));
            Result result = results.get(new NaturalNumber(resultNo));
            winners.add(playerName, result);
        }

        return new WinnerVO(winners);
    }

    private static LadderResultDto convertLadderResultDto(WinnerVO winnerVO, MadeLadderVO madeLadderVO) {
        LadderResultDto ladderResultDto = new LadderResultDto();
        ladderResultDto.setMadeLadderVO(madeLadderVO);
        ladderResultDto.setWinnerVO(winnerVO);

        return ladderResultDto;
    }
}

