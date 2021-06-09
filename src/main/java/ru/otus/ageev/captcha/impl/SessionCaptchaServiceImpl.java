package ru.otus.ageev.captcha.impl;

import org.apache.log4j.Logger;
import ru.otus.ageev.constants.WebConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.System.currentTimeMillis;

public class SessionCaptchaServiceImpl extends AbstractCaptchaServiceImpl {
    final static Logger LOG = Logger.getLogger(SessionCaptchaServiceImpl.class);

    @Override
    public void initCaptchaValue(HttpServletRequest request, HttpServletResponse response) {
        Long timeGenerated = currentTimeMillis();
        String captchaValue = generateCaptchaValue();
        captchaMap.put(timeGenerated, captchaValue);
        request.getSession().setAttribute(WebConstant.CAPTCHA_VALUE, captchaValue);
        request.getSession().setAttribute(WebConstant.CAPTCHA_TIME_CREATED, timeGenerated);
        LOG.debug(getClass() + " captcha init to session " + captchaValue);
    }

    @Override
    public String getCaptchaValue(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(WebConstant.CAPTCHA_VALUE);
    }

    @Override
    public Long getTimeCreatedCaptcha(HttpServletRequest request) {
        return (Long) request.getSession().getAttribute(WebConstant.CAPTCHA_TIME_CREATED);
    }
}
