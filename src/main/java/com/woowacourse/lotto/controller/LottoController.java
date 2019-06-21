package com.woowacourse.lotto.controller;

import com.woowacourse.lotto.WebUILottoApplication;
import com.woowacourse.lotto.domain.LottoBuyList;
import com.woowacourse.lotto.domain.dto.LottoBuyDto;
import com.woowacourse.lotto.service.LottoBuyWebService;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class LottoController {
    private static LottoBuyWebService lottoBuyService;

    public static Object init(Request request, Response response) {
        String moneyInput = request.queryParams("money");
        String manualNumberInput = request.queryParams("manualNumber");
        String[] manualLottos = request.queryParamsValues("manualLotto");
        int round = request.session().attribute("round");

        lottoBuyService = new LottoBuyWebService(moneyInput, manualNumberInput, manualLottos, round);
        LottoBuyList totalBuys = lottoBuyService.createTotalBuyList();

        request.session().attribute("money", lottoBuyService.getPurchaseMoney());
        request.session().attribute("buyList", totalBuys);

        LottoBuyDto lottoBuyDto = new LottoBuyDto(totalBuys.getLottoTickets());

        Map<String, Object> model = new HashMap<>();
        model.put("lottoBuyDto", lottoBuyDto);
        return WebUILottoApplication.render(model, "buylist.html");
    }
}
