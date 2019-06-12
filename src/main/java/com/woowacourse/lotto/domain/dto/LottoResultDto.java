package com.woowacourse.lotto.domain.dto;

public class LottoResultDto {
    private String resultMsg;
    private double profitRatio;

    public LottoResultDto(String resultMsg, double profitRatio) {
        this.resultMsg = resultMsg;
        this.profitRatio = profitRatio;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public double getProfitRatio() {
        return profitRatio;
    }

    public void setProfitRatio(double profitRatio) {
        this.profitRatio = profitRatio;
    }
}
