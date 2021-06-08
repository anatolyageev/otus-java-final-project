package ru.otus.ageev.web.filter;

import ru.otus.ageev.web.filter.wrappers.GzipWrapper;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


import static ru.otus.ageev.constants.WebConstant.ACCEPT_ENCODING;
import static ru.otus.ageev.constants.WebConstant.CONTENT_ENCODING;
import static ru.otus.ageev.constants.WebConstant.GZIP;


public class GzipFilter implements Filter {
    final static Logger LOG = Logger.getLogger(GzipFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("GzipFilter was init.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String acceptEncoding = httpServletRequest.getHeader(ACCEPT_ENCODING);
        if (Objects.nonNull(acceptEncoding) && acceptEncoding.contains(GZIP)) {
            httpServletResponse.addHeader(CONTENT_ENCODING, GZIP);
            GzipWrapper gzipResponse = new GzipWrapper(httpServletResponse);
            chain.doFilter(httpServletRequest, gzipResponse);
            gzipResponse.close();
        } else {
            chain.doFilter(httpServletRequest, httpServletResponse);
        }
        LOG.debug("GzipFilter doFilter().");
    }

    @Override
    public void destroy() {
        LOG.debug("GzipFilter was destroyed.");
    }
}
