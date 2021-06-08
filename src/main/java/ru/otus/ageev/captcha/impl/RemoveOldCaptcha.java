package ru.otus.ageev.captcha.impl;

import ru.otus.ageev.captcha.CaptchaService;
import org.apache.log4j.Logger;

import java.util.Map;

import static java.lang.System.currentTimeMillis;

public class RemoveOldCaptcha implements Runnable {
    final static Logger LOG = Logger.getLogger(RemoveOldCaptcha.class);
    private final Map<Long, String> captchaMap;
    private final Long captchaLiveTime;

    public RemoveOldCaptcha(CaptchaService captchaService, Long captchaLiveTime) {
        this.captchaMap = captchaService.getCaptchaMap();
        this.captchaLiveTime = captchaLiveTime;
    }

    @Override
    public void run() {
        Long currentTime = currentTimeMillis();
        for (Map.Entry<Long, String> e : captchaMap.entrySet()) {
            LOG.debug("Captcha livetime" + (currentTime - e.getKey()));
            if (currentTime - e.getKey() >= captchaLiveTime) {
                String captcha = e.getValue();
                captchaMap.remove(e.getKey());
                LOG.debug("Timer triggered on " + (currentTime - e.getKey()));
                LOG.debug("Captcha " + captcha + " removed!");
            }
        }
    }
}
