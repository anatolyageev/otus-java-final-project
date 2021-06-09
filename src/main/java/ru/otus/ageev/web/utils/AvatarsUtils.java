package ru.otus.ageev.web.utils;

import ru.otus.ageev.exeptions.AvatarException;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


import static ru.otus.ageev.constants.WebConstant.SERVER_USER_DIR;
import static ru.otus.ageev.constants.WebConstant.USER_AVATAR;

public class AvatarsUtils {
    final static Logger LOG = Logger.getLogger(AvatarsUtils.class);
    private static String serverPath;

    private static final String AVATAR_FOLDER = File.separator + "avatars" + File.separator;

    private static void checkPath() {
        serverPath = System.getProperty(SERVER_USER_DIR) + AVATAR_FOLDER;
        File path = new File(serverPath);
        if (!path.exists()) {
            path.mkdir();
        }
    }

    public static String getServerPath() {
        checkPath();
        return serverPath;
    }

    public static void saveAvatar(HttpServletRequest request, String login) throws ServletException, AvatarException {
        checkPath();
        final String saveAvatarPath = serverPath + login + ".png";
        LOG.debug("File save path: " + saveAvatarPath);
        try (InputStream inputStream = request.getPart(USER_AVATAR).getInputStream()) {
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            ImageIO.write(bufferedImage, "png", new File(saveAvatarPath));
        } catch (IOException e) {
            LOG.error("File save path: " + saveAvatarPath);
            throw new AvatarException("File cannot be saved.");
        }
    }
}
