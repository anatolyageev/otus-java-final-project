package ru.otus.ageev.captcha.impl;

import org.apache.log4j.Logger;
import ru.otus.ageev.constants.WebConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

import static java.lang.System.currentTimeMillis;

public class HiddenFieldCaptchaServiceImpl extends AbstractCaptchaServiceImpl {
    final static Logger LOG = Logger.getLogger(HiddenFieldCaptchaServiceImpl.class);

    @Override
    public void initCaptchaValue(HttpServletRequest request, HttpServletResponse response) {
        Long timeGenerated = currentTimeMillis();
        String captchaValue = generateCaptchaValue();
        captchaMap.put(timeGenerated, captchaValue);
        request.setAttribute(WebConstant.HIDDEN_FIELD, timeGenerated);
    }

    @Override
    public String getCaptchaValue(HttpServletRequest request) {
        LOG.debug("Get captcha is: " + captchaMap.get(getTimeCreatedCaptcha(request)));
        return captchaMap.get(getTimeCreatedCaptcha(request));
    }

    @Override
    public Long getTimeCreatedCaptcha(HttpServletRequest request) {
        String timeValue = (String) request.getParameter(WebConstant.HIDDEN_FIELD);
        LOG.debug("Hidden time: " + timeValue);
        if (Objects.isNull(timeValue)) {
            return Long.MIN_VALUE;
        }
        return Long.parseLong(timeValue);
    }
}
