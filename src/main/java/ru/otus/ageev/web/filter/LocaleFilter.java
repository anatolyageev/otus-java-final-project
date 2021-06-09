package ru.otus.ageev.web.filter;


import ru.otus.ageev.web.filter.service.LocaleService;
import ru.otus.ageev.web.filter.service.impl.LocaleServiceCookieImpl;
import ru.otus.ageev.web.filter.service.impl.LocaleServiceSessionImpl;
import ru.otus.ageev.web.filter.wrappers.LocalizationWrapper;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
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


import static ru.otus.ageev.constants.WebConstant.COOKIE_AGE;
import static ru.otus.ageev.constants.WebConstant.COOKIE_CONST;
import static ru.otus.ageev.constants.WebConstant.LOCALE;
import static ru.otus.ageev.constants.WebConstant.LOCALE_DEFAULT;
import static ru.otus.ageev.constants.WebConstant.LOCALE_PARAMETERS;
import static ru.otus.ageev.constants.WebConstant.LOCALE_STORAGE;

public class LocaleFilter implements Filter {
    final static Logger LOG = Logger.getLogger(LocaleFilter.class);

    private String localeDefault;
    private String localeParams;
    private LocaleService localeService;

    public void init(FilterConfig filterConfig) {
        localeDefault = filterConfig.getInitParameter(LOCALE_DEFAULT);
        localeParams = filterConfig.getInitParameter(LOCALE_PARAMETERS);
        String localeStorage = filterConfig.getInitParameter(LOCALE_STORAGE);
        if (COOKIE_CONST.equals(localeStorage)) {
            localeService = new LocaleServiceCookieImpl(Integer.parseInt(filterConfig.getInitParameter(COOKIE_AGE)));
        } else {
            localeService = new LocaleServiceSessionImpl();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String locale = httpServletRequest.getParameter(LOCALE);
        LocalizationWrapper localizationWrapper;
        if (Objects.isNull(locale)) {
            locale = localeService.getLocale(httpServletRequest);
            if (Objects.isNull(locale)) {
                locale = findMostPreferableLocale(httpServletRequest);
            }
            if (Objects.isNull(locale)) {
                locale = getLocaleFromBrowser(httpServletRequest);
            }

            if (Objects.isNull(locale)) {
                localizationWrapper = new LocalizationWrapper(httpServletRequest, localeDefault);
            } else {
                localeService.setLocale(httpServletRequest, httpServletResponse, new Locale(locale));

                localizationWrapper = new LocalizationWrapper(httpServletRequest, locale);
            }
        } else {
            localeService.setLocale(httpServletRequest, httpServletResponse, new Locale(locale));
            localizationWrapper = new LocalizationWrapper(httpServletRequest, locale);
        }
        LOG.debug("locale is: " + locale);
        chain.doFilter(localizationWrapper, httpServletResponse);
    }

    private String findMostPreferableLocale(HttpServletRequest httpServletRequest) {
        for (Locale locale : Collections.list(httpServletRequest.getLocales())) {
            if (localeParams.contains(locale.toString())) {
                return locale.toString();
            }
        }
        return null;
    }

    private String getLocaleFromBrowser(HttpServletRequest httpServletRequest) {
        Enumeration<Locale> browserLocales = httpServletRequest.getLocales();
        Locale locale;
        while (browserLocales.hasMoreElements()) {
            locale = browserLocales.nextElement();
            if (localeParams.contains(locale.getLanguage())) {
                return locale.toString();
            }
        }
        return null;
    }
}
