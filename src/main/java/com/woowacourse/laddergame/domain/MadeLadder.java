package com.woowacourse.laddergame.domain;

import com.woowacourse.laddergame.domain.vo.LadderGameResultVO;
import com.woowacourse.laddergame.util.NaturalNumber;

public class MadeLadder {
    private Players players;
    private Ladder ladder;
    private Results results;

    public MadeLadder(Players players, Ladder ladder, Results results) {
        this.players = players;
        this.ladder = ladder;
        this.results = results;
    }

    public Players getPlayers() {
        return players;
    }

    public String getLadderShape() {
        return ladder.toString();
    }

    public String getPlayerNames() {
        return players.toString();
    }

    public String getLadderResult() {
        return results.toString();
    }

    public LadderGameResultVO takeLadder(String name) {
        if (!players.isContains(name)) {
            throw new IllegalArgumentException("사용자 이름이 없습니다");
        }

        int playerNo = players.getPlayerNo(name);
        int resultNo = ladder.takeLadder(new NaturalNumber(playerNo));
        Result result = results.get(new NaturalNumber(resultNo));

        LadderGameResultVO ladderGameResultVO = new LadderGameResultVO();
        ladderGameResultVO.setPlayerName(name);
        ladderGameResultVO.setResult(result.getResult());

        return ladderGameResultVO;
    }
}
