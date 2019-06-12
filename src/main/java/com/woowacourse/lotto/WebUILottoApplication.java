package com.woowacourse.lotto;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.domain.dto.LottoBuyDto;
import com.woowacourse.lotto.domain.dto.LottoResultDto;
import com.woowacourse.lotto.domain.repository.LottoRepository;
import com.woowacourse.lotto.service.WebLottoService;
import com.woowacourse.lotto.view.OutputMessageConverter;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUILottoApplication {
    private static WebLottoService webLottoService = new WebLottoService();

    public static void main(String[] args) {
        get("/", (req, res) -> {
            // 회차의 증가
            int round = LottoRepository.selectRound();
            LottoRepository.addRound(round);

            req.session().attribute("round", round + 1);

            Map<String, Object> model = new HashMap<>();
            model.put("round", round + 1);
            return render(model, "index.html");
        });

        post("/lotto", (req, res) -> {
            // 파라미터 받기
            String moneyInput = req.queryParams("money");
            String manualNumberInput = req.queryParams("manualNumber");
            String[] manualLottos = req.queryParamsValues("manualLotto");

            // 서비스 호출
            List<Lotto> manualLottoList = new ArrayList<>();
            PurchaseMoney purchaseMoney = webLottoService.createPurchaseMoney(moneyInput);
            ManualNumber manualNumber = webLottoService.createManualNumber(manualNumberInput, purchaseMoney);

            if (manualLottos != null) {
                addManualLotto(manualLottos, manualLottoList);
            }

            LottoBuyList totalBuys = webLottoService.createTotalBuyList(new LottoBuyList(manualLottoList), purchaseMoney, manualNumber);

            req.session().attribute("buyList", totalBuys);
            req.session().attribute("money", purchaseMoney);

            // Dto 생성
            LottoBuyDto lottoBuyDto = new LottoBuyDto(totalBuys.getLottoTickets());

            // View 에 내리기
            Map<String, Object> model = new HashMap<>();
            model.put("lottoBuyDto", lottoBuyDto);
            return render(model, "buylist.html");
        });

        post("/winning", (req, res) -> {
            // 파라미터 받기
            String lottoInput = req.queryParams("winningLotto");
            String bonusNumberInput = req.queryParams("bonusNumber");

            // 서비스 호출
            Lotto lastWeekLotto = webLottoService.createLotto(lottoInput);
            LottoNumber bonusNumber = webLottoService.createBonusNumber(bonusNumberInput, lastWeekLotto);
            LottoBuyList totalBuys = req.session().attribute("buyList");
            LottoResult lottoResult = webLottoService.createResult(totalBuys, lastWeekLotto, bonusNumber);

            LottoResultDto lottoResultDto =
                    new LottoResultDto(OutputMessageConverter.makeHittingStatusMsg(lottoResult),
                            lottoResult.calculateProfitRatio(req.session().attribute("money")));

            // View 에 내리기
            Map<String, Object> model = new HashMap<>();
            model.put("result", lottoResultDto);

            return render(model, "result.html");
        });
    }

    private static void addManualLotto(String[] manualLottos, List<Lotto> manualLottoList) {
        for (String manualLotto : manualLottos) {
            webLottoService.addManualLotto(manualLottoList, manualLotto);
        }
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
