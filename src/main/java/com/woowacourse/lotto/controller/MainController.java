package com.woowacourse.lotto.controller;

import com.woowacourse.lotto.WebUILottoApplication;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class MainController {
    public static Object init(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        return WebUILottoApplication.render(model, "index.html");
    }
}
