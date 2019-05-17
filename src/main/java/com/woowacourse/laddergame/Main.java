package com.woowacourse.laddergame;

import com.woowacourse.laddergame.domain.vo.LadderDto;
import com.woowacourse.laddergame.domain.vo.LadderResultDto;
import com.woowacourse.laddergame.domain.vo.ResultNameDto;
import com.woowacourse.laddergame.service.LadderGameService;
import com.woowacourse.laddergame.view.InputView;
import com.woowacourse.laddergame.view.OutputView;

public class Main {

    public static void main(String[] args) {
        // view 호출
        LadderDto ladderDto = new LadderDto();
        InputView.inputPlayerNames(ladderDto);
        InputView.inputHeight(ladderDto);
        InputView.inputGameResult(ladderDto);

        // GameService 인자로 넘긴다 - 사다리 vo 반환
        LadderGameService ladderGameService = new LadderGameService();
        ladderGameService.play(ladderDto);

        LadderResultDto ladderResultDto = ladderGameService.play(ladderDto);

        OutputView.printLadderStatus(ladderResultDto);

        while (true) {
            ResultNameDto resultNameDto = new ResultNameDto();
            InputView.inputResultName(resultNameDto);
            String targetName = resultNameDto.getName();
            OutputView.printLadderGameResult(targetName, ladderResultDto);
        }
    }
}
