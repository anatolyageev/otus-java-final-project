package ru.otus.ageev.web.servlets;

import ru.otus.ageev.captcha.CaptchaService;
import ru.otus.ageev.web.utils.LoginUtils;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;


import static ru.otus.ageev.constants.WebConstant.CAPTCHA_STRATEGY;

@WebServlet("/img/captcha")
public class CaptchaGenerate extends HttpServlet {
    final static Logger LOG = Logger.getLogger(CaptchaGenerate.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CaptchaService captchaService = (CaptchaService) req.getServletContext().getAttribute(CAPTCHA_STRATEGY);
        try (OutputStream outputStream = resp.getOutputStream()) {
            String captcha = captchaService.getCaptchaValue(req);
            LOG.debug("Captcha from CapGen: " + captcha);
            outputStream.write(LoginUtils.generateImage(captcha));
        }
    }
}
