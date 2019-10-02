package nextstep.mvc.asis;

import nextstep.mvc.tobe.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller extends Handler {
    String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
