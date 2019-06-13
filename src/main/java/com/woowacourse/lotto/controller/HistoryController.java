package com.woowacourse.lotto.controller;

import com.woowacourse.lotto.WebUILottoApplication;
import com.woowacourse.lotto.domain.History;
import com.woowacourse.lotto.domain.dto.HistoryDto;
import com.woowacourse.lotto.service.HistoryWebService;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class HistoryController {
    private static HistoryWebService historyWebService = new HistoryWebService();

    public static Object init(Request request, Response response) throws SQLException {
        String round = request.queryParams("history");
        History history = historyWebService.createHistory(round);

        HistoryDto historyDto = new HistoryDto();
        historyDto.setProfitRatio(String.format("%.1f", history.profitRatio()));
        historyDto.setResultMsg(history.resultMsg());
        historyDto.setTickets(history.tickets());
        historyDto.setWinningTicket(history.getWinningTicket());
        historyDto.setBonusNumber(history.getBonusNumber());
        historyDto.setWinningMoney(String.format("%.1f", history.winningMoney()));

        Map<String, Object> model = new HashMap<>();
        model.put("history", historyDto);
        return WebUILottoApplication.render(model, "history.html");
    }
}
