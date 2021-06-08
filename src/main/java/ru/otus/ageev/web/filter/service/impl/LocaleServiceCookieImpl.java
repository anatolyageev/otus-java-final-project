package ru.otus.ageev.web.filter.service.impl;

import ru.otus.ageev.web.filter.service.LocaleService;

import java.util.Locale;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import static ru.otus.ageev.constants.WebConstant.LOCALE;

public class LocaleServiceCookieImpl implements LocaleService {
    private int cookiesAge;

    public LocaleServiceCookieImpl(int cookiesAge) {
        this.cookiesAge = cookiesAge;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {
        Cookie cookie = new Cookie(LOCALE, locale.toString());
        cookie.setMaxAge(cookiesAge);
        httpServletResponse.addCookie(cookie);
    }

    @Override
    public String getLocale(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(LOCALE)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
