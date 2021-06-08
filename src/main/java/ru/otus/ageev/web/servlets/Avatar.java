package ru.otus.ageev.web.servlets;

import ru.otus.ageev.web.utils.AvatarsUtils;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


import static ru.otus.ageev.constants.WebConstant.AVATAR_PNG_EXTENSION;
import static ru.otus.ageev.constants.WebConstant.AVATAR_PNG_FORMAT_NAME;
import static ru.otus.ageev.constants.WebConstant.LOGIN_USER;

@WebServlet("/getAvatar")
public class Avatar extends HttpServlet {
    final static Logger LOG = Logger.getLogger(Avatar.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.debug(getClass() + " doGet() started");
        String currentUserLogin = (String) req.getSession().getAttribute(LOGIN_USER);
        File avatarFile = new File(AvatarsUtils.getServerPath() + currentUserLogin + AVATAR_PNG_EXTENSION);
        ImageIO.write(ImageIO.read(avatarFile), AVATAR_PNG_FORMAT_NAME, resp.getOutputStream());
        LOG.debug(getClass() + " doGet() finished");
    }
}
