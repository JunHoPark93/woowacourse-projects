package com.woowacourse.lotto.controller;

import com.woowacourse.lotto.WebUILottoApplication;
import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoBuyList;
import com.woowacourse.lotto.domain.LottoNumber;
import com.woowacourse.lotto.domain.LottoResult;
import com.woowacourse.lotto.domain.dto.LottoResultDto;
import com.woowacourse.lotto.service.LottoService;
import com.woowacourse.lotto.service.WebLottoService;
import com.woowacourse.lotto.view.OutputMessageConverter;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class WinningController {
    private static WebLottoService webLottoService = new WebLottoService();

    public static Object init(Request request, Response response) {
        String lottoInput = request.queryParams("winningLotto");
        String bonusNumberInput = request.queryParams("bonusNumber");

        Lotto lastWeekLotto = LottoService.createLotto(lottoInput);
        LottoNumber bonusNumber = LottoService.createBonusNumber(bonusNumberInput, lastWeekLotto);
        LottoBuyList totalBuys = request.session().attribute("buyList");
        LottoResult lottoResult = webLottoService.createResult(totalBuys, lastWeekLotto, bonusNumber);

        LottoResultDto lottoResultDto =
                new LottoResultDto(OutputMessageConverter.makeHittingStatusMsg(lottoResult),
                        String.format("%.1f", lottoResult.calculateProfitRatio(request.session().attribute("money"))));

        Map<String, Object> model = new HashMap<>();
        model.put("result", lottoResultDto);

        return WebUILottoApplication.render(model, "result.html");
    }
}
