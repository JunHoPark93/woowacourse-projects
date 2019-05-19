package com.woowacourse.laddergame.service;

import com.woowacourse.laddergame.domain.*;
import com.woowacourse.laddergame.domain.vo.LadderDto;
import com.woowacourse.laddergame.domain.vo.LadderResultDto;
import com.woowacourse.laddergame.domain.vo.MadeLadderVO;
import com.woowacourse.laddergame.domain.vo.WinnerVO;
import com.woowacourse.laddergame.util.BooleanGenerator;
import com.woowacourse.laddergame.util.NaturalNumber;
import com.woowacourse.laddergame.util.RandomBooleanGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

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
        String[] playerNameTokens = ladderDto.getNames().split(",");
        List<Player> players = new ArrayList<>();

        for (String name : playerNameTokens) {
            players.add(new Player(name));
        }

        return new Players(players);
    }

    private static Results getResults(LadderDto ladderDto) {
        String[] resultNameTokens = ladderDto.getResult().split(",");
        List<Result> results = new ArrayList<>();

        for (String result : resultNameTokens) {
            results.add(new Result(result));
        }

        return new Results(results);
    }

    private static Ladder getLadder(LadderDto ladderDto, Players players) {
        int height = ladderDto.getHeight();
        int countOfPerson = players.getPlayerCount();
        BooleanGenerator booleanGenerator = new RandomBooleanGenerator();

        return new Ladder(new NaturalNumber(height), new NaturalNumber(countOfPerson), booleanGenerator);
    }

    private static WinnerVO getWinners(Players players, Ladder ladder, Results results) {
        HashMap<Player, Result> winnerHashMap = new LinkedHashMap<>();
        for (Player player: players.getPlayers()) {
            int resultNo = ladder.takeLadder(new NaturalNumber(players.getPlayerNo(player.getName())));
            Result result = results.get(new NaturalNumber(resultNo));
            winnerHashMap.put(player, result);
        }

        Winners winners = new Winners(winnerHashMap);
        return new WinnerVO(winners);
    }

    private static LadderResultDto convertLadderResultDto(WinnerVO winnerVO, MadeLadderVO madeLadderVO) {
        LadderResultDto ladderResultDto = new LadderResultDto();
        ladderResultDto.setMadeLadderVO(madeLadderVO);
        ladderResultDto.setWinnerVO(winnerVO);

        return ladderResultDto;
    }
}

