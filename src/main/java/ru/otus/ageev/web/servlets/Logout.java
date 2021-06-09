package ru.otus.ageev.web.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;


import static ru.otus.ageev.constants.WebConstant.LOGIN_USER;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    final static Logger LOG = Logger.getLogger(Logout.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.debug("Start logout");
        req.getSession().removeAttribute(LOGIN_USER);
        resp.sendRedirect(req.getHeader("Referer"));
        LOG.debug("Start finish logout");
    }
}
