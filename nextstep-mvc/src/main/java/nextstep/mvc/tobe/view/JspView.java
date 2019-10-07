package nextstep.mvc.tobe.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JspView implements View {
    private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";

    private final String viewName;

    public JspView(String viewName) {
        this.viewName = viewName;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (viewName.startsWith(DEFAULT_REDIRECT_PREFIX)) {
            new RedirectView(extractViewName()).render(model, request, response);
            return;
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewName);
        requestDispatcher.forward(request, response);
    }

    private String extractViewName() {
        return viewName.substring(DEFAULT_REDIRECT_PREFIX.length());
    }
}
