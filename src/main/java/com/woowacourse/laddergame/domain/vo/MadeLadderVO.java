package com.woowacourse.laddergame.domain.vo;

import com.woowacourse.laddergame.domain.Ladder;
import com.woowacourse.laddergame.domain.Players;
import com.woowacourse.laddergame.domain.Results;

public class MadeLadderVO {
    private final Players players;
    private final Ladder ladder;
    private final Results results;

    public MadeLadderVO(Players players, Ladder ladder, Results results) {
        this.players = players;
        this.ladder = ladder;
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MadeLadderVO that = (MadeLadderVO) o;

        if (players != null ? !players.equals(that.players) : that.players != null) return false;
        if (ladder != null ? !ladder.equals(that.ladder) : that.ladder != null) return false;
        return results != null ? results.equals(that.results) : that.results == null;
    }

    @Override
    public int hashCode() {
        int result = players != null ? players.hashCode() : 0;
        result = 31 * result + (ladder != null ? ladder.hashCode() : 0);
        result = 31 * result + (results != null ? results.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(players.toString()).append("\n");
        sb.append(ladder.toString());
        sb.append(results.toString());

        return sb.toString();
    }
}
