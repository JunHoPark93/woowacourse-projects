package com.woowacourse.laddergame.util;

import com.woowacourse.laddergame.domain.vo.LadderResultDto;
import com.woowacourse.laddergame.domain.vo.MadeLadderVO;
import com.woowacourse.laddergame.domain.vo.WinnerVO;

public class ConsoleUtil {
    public static LadderResultDto convertLadderResultDto(WinnerVO winnerVO, MadeLadderVO madeLadderVO) {
        LadderResultDto ladderResultDto = new LadderResultDto();
        ladderResultDto.setMadeLadderVO(madeLadderVO);
        ladderResultDto.setWinnerVO(winnerVO);

        return ladderResultDto;
    }
}
