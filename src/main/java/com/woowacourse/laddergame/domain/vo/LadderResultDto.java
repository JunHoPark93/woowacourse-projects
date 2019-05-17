package com.woowacourse.laddergame.domain.vo;

public class LadderResultDto {
    private static final String PLAY_ALL_LADDER_RESERVED_WORD = "all";

    private MadeLadderVO madeLadderVO;
    private WinnerVO winnerVO;

    public String getResult(String name) {
        if (name.equals(PLAY_ALL_LADDER_RESERVED_WORD)) {
            return winnerVO.getAllResult();
        }
        if (!winnerVO.isContains(name)) {
            throw new IllegalArgumentException("존재하지 않는 이름입니다");
        }
        return winnerVO.getSingleResult(name) + "\n";
    }

    public MadeLadderVO getMadeLadderVO() {
        return madeLadderVO;
    }

    public void setMadeLadderVO(MadeLadderVO madeLadderVO) {
        this.madeLadderVO = madeLadderVO;
    }

    public WinnerVO getWinnerVO() {
        return winnerVO;
    }

    public void setWinnerVO(WinnerVO winnerVO) {
        this.winnerVO = winnerVO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LadderResultDto that = (LadderResultDto) o;

        if (madeLadderVO != null ? !madeLadderVO.equals(that.madeLadderVO) : that.madeLadderVO != null) return false;
        return winnerVO != null ? winnerVO.equals(that.winnerVO) : that.winnerVO == null;
    }

    @Override
    public int hashCode() {
        int result = madeLadderVO != null ? madeLadderVO.hashCode() : 0;
        result = 31 * result + (winnerVO != null ? winnerVO.hashCode() : 0);
        return result;
    }
}
