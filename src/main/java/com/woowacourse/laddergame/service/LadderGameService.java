package com.woowacourse.laddergame.service;

import com.woowacourse.laddergame.domain.*;
import com.woowacourse.laddergame.domain.vo.LadderDto;
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
    private Players players;
    private Results results;
    private Ladder ladder;

    public LadderGameService(LadderDto ladderDto) {
        this.players = getPlayers(ladderDto);
        this.results = getResults(ladderDto);
        this.ladder = getLadder(ladderDto, players);
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

    public MadeLadderVO getLadderResult() {
        return new MadeLadderVO(players, ladder, results);
    }

    public WinnerVO play() {
        return getWinners(players, ladder, results);
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
}

