package com.woowacourse.laddergame;

import com.woowacourse.laddergame.domain.vo.*;
import com.woowacourse.laddergame.service.LadderGameService;
import com.woowacourse.laddergame.util.ConsoleUtil;
import com.woowacourse.laddergame.view.InputView;
import com.woowacourse.laddergame.view.OutputView;

public class Main {

    public static void main(String[] args) {
        LadderDto ladderDto = new LadderDto();
        InputView.inputPlayerNames(ladderDto);
        InputView.inputHeight(ladderDto);
        InputView.inputGameResult(ladderDto);

        LadderGameService ladderGameService = new LadderGameService(ladderDto);

        MadeLadderVO madeLadderVO = ladderGameService.getLadderResult();
        WinnerVO winnerVO = ladderGameService.play();

        LadderResultDto ladderResultDto = ConsoleUtil.convertLadderResultDto(winnerVO, madeLadderVO);
        OutputView.printLadderStatus(ladderResultDto);

        while (true) {
            ResultNameDto resultNameDto = new ResultNameDto();
            InputView.inputResultName(resultNameDto);
            String targetName = resultNameDto.getName();
            OutputView.printLadderGameResult(targetName, ladderResultDto, resultNameDto);
        }
    }
}
