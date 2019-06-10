package com.woowacourse.lotto.domain.dto;

public class LottoResultDto {
    private String hittingStatusMsg;
    private String profitRatioMsg;

    public String getHittingStatusMsg() {
        return hittingStatusMsg;
    }

    public void setHittingStatusMsg(String hittingStatusMsg) {
        this.hittingStatusMsg = hittingStatusMsg;
    }

    public String getProfitRatioMsg() {
        return profitRatioMsg;
    }

    public void setProfitRatioMsg(String profitRatioMsg) {
        this.profitRatioMsg = profitRatioMsg;
    }
}
