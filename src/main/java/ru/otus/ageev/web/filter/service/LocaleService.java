package ru.otus.ageev.web.filter.service;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public interface LocaleService {
    void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale);

    String getLocale(HttpServletRequest httpServletRequest);
}
