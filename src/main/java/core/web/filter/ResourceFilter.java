package core.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/*")
public class ResourceFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(ResourceFilter.class);
    private static final List<String> resourcePrefixs = new ArrayList<>();

    static {
        resourcePrefixs.add("/css");
        resourcePrefixs.add("/js");
        resourcePrefixs.add("/fonts");
        resourcePrefixs.add("/images");
        resourcePrefixs.add("/favicon.ico");
    }

    private RequestDispatcher defaultRequestDispatcher;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.defaultRequestDispatcher = filterConfig.getServletContext().getNamedDispatcher("default");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getRequestURI().substring(req.getContextPath().length());
        if (isResourceUrl(path)) {
            logger.debug("path : {}", path);
            defaultRequestDispatcher.forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean isResourceUrl(String url) {
        for (String prefix : resourcePrefixs) {
            if (url.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {
    }

}
