package ru.otus.ageev.web.servlets;

import ru.otus.ageev.domain.User;
import ru.otus.ageev.services.UserService;
import ru.otus.ageev.web.utils.LoginUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;


import static ru.otus.ageev.constants.WebConstant.LOGIN_ERROR;
import static ru.otus.ageev.constants.WebConstant.LOGIN_USER;
import static ru.otus.ageev.constants.WebConstant.USER_ID;
import static ru.otus.ageev.constants.WebConstant.USER_PASSWORD;
import static ru.otus.ageev.constants.WebConstant.USER_ROLE;
import static ru.otus.ageev.constants.WebConstant.USER_SERVICE;

@WebServlet("/login")
public class Login extends HttpServlet {
    final static Logger LOG = Logger.getLogger(Login.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug(getClass() + " doGet() started");
        req.getRequestDispatcher(req.getHeader("Referer")).forward(req, resp);
        LOG.debug(getClass() + " doGet() ended");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.debug(getClass() + " doPost() started");
        Map<String, String> errors = LoginUtils.checkLogin(req);
        if (!errors.isEmpty()) {
            req.getSession().setAttribute(LOGIN_ERROR, "Login and password does not correct");
            resp.sendRedirect("login");
            return;
        }

        String login = req.getParameter(USER_ID);
        String password = req.getParameter(USER_PASSWORD);
        LOG.debug(String.format("Url form request ==> %s", req.getRequestURL()));
        LOG.debug(String.format("Params from reqest ==> %s;%s;  ", login, password));

        UserService userService = (UserService) req.getServletContext().getAttribute(USER_SERVICE);
        if (userService.checkUserExistInDb(login)) {
            User user = userService.getOne(login);
            LOG.debug(String.format("User from db ==> %s", user));
            if (Objects.equals(user.getPassword(), password)) {
                req.getSession().setAttribute(LOGIN_USER, login);
                req.getSession().setAttribute(USER_ROLE, user.getUserRole().toString());
            }
        }
        resp.sendRedirect(req.getHeader("Referer"));
        LOG.debug(getClass() + " doPost() finished");
    }
}
