package com.woowacourse.laddergame.domain.vo;

public class LadderStatusVO {
    private String playerNames;
    private String ladderShape;
    private String ladderResult;

    public String getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(String playerNames) {
        this.playerNames = playerNames;
    }

    public String getLadderShape() {
        return ladderShape;
    }

    public void setLadderShape(String ladderShape) {
        this.ladderShape = ladderShape;
    }

    public String getLadderResult() {
        return ladderResult;
    }

    public void setLadderResult(String ladderResult) {
        this.ladderResult = ladderResult;
    }
}
