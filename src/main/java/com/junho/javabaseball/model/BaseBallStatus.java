package com.junho.javabaseball.model;

/**
 * @author junho.park
 */
public class BaseBallStatus {
    private int strike;
    private int ball;

    public BaseBallStatus(int strike, int ball) {
        this.strike = strike;
        this.ball = ball;
    }

    public int getStrike() {
        return strike;
    }

    public void setStrike(int strike) {
        this.strike = strike;
    }

    public int getBall() {
        return ball;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    @Override
    public String toString() {
        if (strike == 0 && ball == 0) {
            return "낫싱";
        } else if (strike == 0 && ball != 0) {
            return ball + "볼";
        } else if (strike != 0 && ball == 0) {
            return strike + " 스트라이크";
        } else {
            return strike + " 스트라이크 " + ball + "볼";
        }
    }
}
