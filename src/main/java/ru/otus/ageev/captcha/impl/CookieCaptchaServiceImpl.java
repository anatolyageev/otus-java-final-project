package ru.otus.ageev.captcha.impl;

import org.apache.log4j.Logger;
import ru.otus.ageev.constants.WebConstant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.lang.System.currentTimeMillis;

public class CookieCaptchaServiceImpl extends AbstractCaptchaServiceImpl {
    final static Logger LOG = Logger.getLogger(CookieCaptchaServiceImpl.class);


    @Override
    public void initCaptchaValue(HttpServletRequest request, HttpServletResponse response) {
        Long timeGenerated = currentTimeMillis();
        String captchaValue = generateCaptchaValue();
        captchaMap.put(timeGenerated, captchaValue);
        response.addCookie(new Cookie(WebConstant.CAPTCHA_TIME_CREATED, timeGenerated.toString()));
    }

    @Override
    public String getCaptchaValue(HttpServletRequest request) {
        return captchaMap.get(getTimeCreatedCaptcha(request));
    }

    @Override
    public Long getTimeCreatedCaptcha(HttpServletRequest request) {
        List<Cookie> cookies = Arrays.asList(request.getCookies());
        Optional<Cookie> cookieVal = cookies.stream()
                .filter(cookie -> WebConstant.CAPTCHA_TIME_CREATED.equals(cookie.getName()))
                .findFirst();
        return cookieVal.map(cookie -> Long.parseLong(cookie.getValue())).orElse(Long.MIN_VALUE);
    }
}
