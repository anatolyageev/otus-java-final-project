package ru.otus.ageev.captcha.impl;

import ru.otus.ageev.captcha.CaptchaService;

import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractCaptchaServiceImpl implements CaptchaService {
    protected Map<Long, String> captchaMap = new ConcurrentHashMap<>();

    public Map<Long, String> getCaptchaMap() {
        return this.captchaMap;
    }

    public void setCaptchaMap(Map<Long, String> captchaMap) {
        this.captchaMap = captchaMap;
    }

    protected String generateCaptchaValue() {
        return new StringTokenizer(UUID.randomUUID().toString(), "-").nextToken();
    }
}
