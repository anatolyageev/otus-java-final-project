package ru.otus.ageev.captcha;

import ru.otus.ageev.captcha.impl.CookieCaptchaServiceImpl;
import ru.otus.ageev.captcha.impl.HiddenFieldCaptchaServiceImpl;
import ru.otus.ageev.captcha.impl.SessionCaptchaServiceImpl;
import ru.otus.ageev.constants.WebConstant;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletContext;

public class CaptchaServiceFactory {
    private static Map<String,CaptchaService> captchaServiceMap;
    static{
        captchaServiceMap = new ConcurrentHashMap<>();
        captchaServiceMap.put(WebConstant.COOKIE_CONST, new CookieCaptchaServiceImpl());
        captchaServiceMap.put(WebConstant.FIELD_CONST, new HiddenFieldCaptchaServiceImpl());
        captchaServiceMap.put(WebConstant.SESSION_CONST, new SessionCaptchaServiceImpl());
    }

    public static CaptchaService getCaptchaService(ServletContext context) {
        String captchaServiceType = context.getInitParameter(WebConstant.CAPTCHA_TYPE);
        return Optional.ofNullable( captchaServiceMap.get(captchaServiceType)).orElse(captchaServiceMap.get(WebConstant.SESSION_CONST));
    }
}
