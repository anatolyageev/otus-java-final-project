package ru.otus.ageev.web.filter;

import ru.otus.ageev.web.filter.service.LocaleService;
import ru.otus.ageev.web.filter.service.impl.LocaleServiceCookieImpl;
import ru.otus.ageev.web.filter.service.impl.LocaleServiceSessionImpl;
import ru.otus.ageev.web.filter.wrappers.LocalizationWrapper;

import javax.servlet.FilterConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static ru.otus.ageev.constants.WebConstant.COOKIE_AGE;
import static ru.otus.ageev.constants.WebConstant.COOKIE_AGE_FOR_TEST;
import static ru.otus.ageev.constants.WebConstant.LOCALE;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;


public class LocaleFilterTest {

    @Mock
    private FilterConfig filterConfig;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpSession session;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getLocaleFromCookie_ShouldReturnNotNull() {
        when(httpServletRequest.getCookies()).thenReturn(new Cookie[]{new Cookie(LOCALE, "ru_Ru")});
        when(httpServletRequest.getSession()).thenReturn(session);
        when(filterConfig.getInitParameter(COOKIE_AGE)).thenReturn(String.valueOf(COOKIE_AGE_FOR_TEST));
        LocaleService localeService = new LocaleServiceCookieImpl(COOKIE_AGE_FOR_TEST);
        String locale = localeService.getLocale(httpServletRequest);
        System.out.println(locale);
        LocalizationWrapper localizationWrapper = new LocalizationWrapper(httpServletRequest, locale);
        assertNotNull(localizationWrapper.getLocale());
    }

    @Test
    public void getLocaleFromSession_ShouldReturnNotNull() {
        LocaleService localeService = new LocaleServiceSessionImpl();

        when(httpServletRequest.getCookies()).thenReturn(null);
        when(httpServletRequest.getSession()).thenReturn(session);
        when(session.getAttribute(LOCALE)).thenReturn("ru_Ru");

        LocalizationWrapper localizationWrapper = new LocalizationWrapper(httpServletRequest, localeService.getLocale(httpServletRequest));
        assertNotNull(localizationWrapper.getLocale());
    }
}