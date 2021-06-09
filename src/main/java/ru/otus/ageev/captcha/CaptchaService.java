package ru.otus.ageev.captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface CaptchaService {

    Map<Long,String> getCaptchaMap();

    void initCaptchaValue(HttpServletRequest request, HttpServletResponse response);

    String getCaptchaValue(HttpServletRequest request);

    Long getTimeCreatedCaptcha(HttpServletRequest request);

    public void setCaptchaMap(Map<Long, String> captchaMap);
}
