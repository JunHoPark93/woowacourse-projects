package com.woowacourse.lotto.controller;

import com.woowacourse.lotto.WebUILottoApplication;
import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoBuyList;
import com.woowacourse.lotto.domain.ManualNumber;
import com.woowacourse.lotto.domain.PurchaseMoney;
import com.woowacourse.lotto.domain.dto.LottoBuyDto;
import com.woowacourse.lotto.service.WebLottoService;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoController {
    private static WebLottoService webLottoService = new WebLottoService();

    public static Object init(Request request, Response response) {
        // 파라미터 받기
        String moneyInput = request.queryParams("money");
        String manualNumberInput = request.queryParams("manualNumber");
        String[] manualLottos = request.queryParamsValues("manualLotto");

        // 서비스 호출
        List<Lotto> manualLottoList = new ArrayList<>();
        PurchaseMoney purchaseMoney = webLottoService.createPurchaseMoney(moneyInput);
        ManualNumber manualNumber = webLottoService.createManualNumber(manualNumberInput, purchaseMoney);

        if (manualLottos != null) {
            addManualLotto(manualLottos, manualLottoList);
        }

        LottoBuyList totalBuys = webLottoService.createTotalBuyList(new LottoBuyList(manualLottoList), purchaseMoney, manualNumber);

        request.session().attribute("buyList", totalBuys);
        request.session().attribute("money", purchaseMoney);

        // Dto 생성
        LottoBuyDto lottoBuyDto = new LottoBuyDto(totalBuys.getLottoTickets());

        // View 에 내리기
        Map<String, Object> model = new HashMap<>();
        model.put("lottoBuyDto", lottoBuyDto);
        return WebUILottoApplication.render(model, "buylist.html");
    }

    private static void addManualLotto(String[] manualLottos, List<Lotto> manualLottoList) {
        for (String manualLotto : manualLottos) {
            webLottoService.addManualLotto(manualLottoList, manualLotto);
        }
    }
}
