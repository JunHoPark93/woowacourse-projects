package com.woowacourse.lotto.controller;

import com.woowacourse.lotto.WebUILottoApplication;
import com.woowacourse.lotto.domain.LottoBuyList;
import com.woowacourse.lotto.domain.LottoResult;
import com.woowacourse.lotto.domain.dto.LottoResultDto;
import com.woowacourse.lotto.service.DrawWebService;
import com.woowacourse.lotto.view.OutputMessageConverter;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class DrawController {
    private static DrawWebService drawService;

    public static Object init(Request request, Response response) {
        String lottoInput = request.queryParams("winningLotto");
        String bonusNumberInput = request.queryParams("bonusNumber");
        LottoBuyList totalBuys = request.session().attribute("buyList");
        int round = request.session().attribute("round");

        drawService = new DrawWebService(totalBuys, lottoInput, bonusNumberInput, round);
        LottoResult lottoResult = drawService.createResult();

        LottoResultDto lottoResultDto =
                new LottoResultDto(OutputMessageConverter.makeHittingStatusMsg(lottoResult),
                        lottoResult.calculateProfitRatio(request.session().attribute("money")));

        Map<String, Object> model = new HashMap<>();
        model.put("result", lottoResultDto);

        return WebUILottoApplication.render(model, "result.html");
    }
}
