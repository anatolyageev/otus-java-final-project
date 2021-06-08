package ru.otus.ageev.web.filter.wrappers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;

public class LocalizationWrapper extends HttpServletRequestWrapper {
    private String locale;

    public LocalizationWrapper(HttpServletRequest request, String locale) {
        super(request);
        this.locale = locale;
    }

    @Override
    public Locale getLocale() {
        return new Locale(locale);
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return Collections.enumeration(Collections.singletonList((new Locale(locale))));
    }
}
