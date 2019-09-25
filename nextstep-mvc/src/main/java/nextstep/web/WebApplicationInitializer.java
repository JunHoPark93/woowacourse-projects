package nextstep.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public interface WebApplicationInitializer {
    void onStartup(ServletContext servletContext) throws ServletException;
}
